package ensamblador.planificacionDinamica.tomasulo.actions;

import java.util.Vector;

import ensamblador.componentes.fus.estacionTomasulo.ERTomasulo;
import ensamblador.componentes.fus.estacionTomasulo.EstadoEstacion;
import ensamblador.datos.Palabra;
import ensamblador.datos.PalabraCompuesta;
import ensamblador.datos.Tipos;
import ensamblador.estado.EstadoInstruccion;
import ensamblador.instrucciones.Instruccion;
import ensamblador.instrucciones.PlanifDinamica;
import ensamblador.instrucciones.TipoI;
import ensamblador.planificacionDinamica.tomasulo.Tomasulo;
import ensamblador.registros.Registro;
import ensamblador.util.action.Accion;
import ensamblador.util.excepcion.ERsOcupadasException;
import ensamblador.util.excepcion.EjecucionException;

public class AvanzarCicloAccion extends Accion {
	private long pcActual;
	private Instruccion instruccion;
	private EstadoInstruccion estadoInstruccion;
	private Tomasulo tomasulo;
	private Vector<ERTomasulo> fin;
	
	
	public AvanzarCicloAccion(long pcActual, Instruccion instruccion, Tomasulo tomasulo) {
		super();
		this.tomasulo=tomasulo;
		this.pcActual=pcActual;	
		this.instruccion=instruccion;
		this.fin=new Vector<ERTomasulo>();
	}
	
	@Override
	public void ejecutar() throws EjecucionException{
		
		tomasulo.setEjecutandoSalto(false);
		
		tomasulo.setEjecutarIF(true);
		avanzarEscritura();
		avanzarEjecucion();
		if(tomasulo.getInstruccionDecodif()!=null){
			
			if(avanzarEmision(tomasulo.getInstruccionDecodif(), tomasulo.getNumInstruccion())){
				
				tomasulo.setEjecutarIF(true);
				tomasulo.setNumInstruccion(tomasulo.getNumInstruccion()+1);
			}else{
				tomasulo.setEjecutarIF(false);
			}
		}
		
		finalizarCiclo();
		avanzarIF(pcActual, instruccion, tomasulo.getNumInstruccion());
		tomasulo.getEstadoInstrucciones().marcarCambios();
		
	}
	
	
	private void avanzarIF(long pcActual, Instruccion instruccion, int numInstruccion){
		
		EstadoInstruccion estadoInstruccion=new EstadoInstruccion(instruccion, pcActual);
		if(!tomasulo.isPipelineDetenido()){
			if(instruccion!=null){
				
				if(!tomasulo.getEstadoInstrucciones().isRegistradaInicio(numInstruccion)){					
					tomasulo.getEstadoInstrucciones().registrar(numInstruccion, estadoInstruccion);
					tomasulo.getEmision().add(estadoInstruccion);
					
				}
			}
		}
		
	}
	
	
	
	private boolean avanzarEmision(EstadoInstruccion instr, int numInstruccion){
		boolean ejecutado=false;
		ERTomasulo estacionReserva=null;		
		EstadoEstacion estado=null;
		EstadoInstruccion estadoInstruccion=instr;
		String qi=null;
		
		estadoInstruccion=tomasulo.getEstadoInstrucciones().getEstado(numInstruccion);
		
		tomasulo.mirarSalto(estadoInstruccion.getInstruccion());
		if((tomasulo.hayERLibre(estadoInstruccion.getInstruccion().getTipoER()))){
			/*Si hay al menos una estacion de reserva del tipo que quiere
			 * la instruccion libre*/
			
			try{
				estacionReserva=tomasulo.ocuparER(estadoInstruccion.getInstruccion().getTipoER());
				estacionReserva.getEstado().setOp(estadoInstruccion.getInstruccion().getOperacion());
				estadoInstruccion.setStatus(Tipos.EMISION);
				ejecutado=true;
			}catch(ERsOcupadasException e){
				System.out.println("InternalErrorException");
			}
			
			estado=(EstadoEstacion)estacionReserva.getEstado();
			
			
			
			if(estadoInstruccion.getInstruccion().getRegRS()!=null){
//				System.out.println("registro-->"+estadoInstruccion.getInstruccion().getRegRS().toString());
//				System.out.println("qi-->"+estadoInstruccion.getInstruccion().getRegRS().getQi());
			}
			
			if(tomasulo.isOperacionFP(estadoInstruccion.getInstruccion().getTipoER())){
				
				if(estadoInstruccion.getInstruccion().getRegRS()!=null){
					if(!((qi=estadoInstruccion.getInstruccion().getRegRS().getQi()).equals(new String()))){				
						estado.setQj(qi);
						
					}else{
						if(estadoInstruccion.getInstruccion().isInstruccionDouble()){
							Instruccion instruccion=estadoInstruccion.getInstruccion();
							Registro rs=instruccion.getRegRS();
							Registro sig=instruccion.relacionaRegF(rs.getSiguiente());							
							PalabraCompuesta Vj=new PalabraCompuesta(rs.getPalabra().getHex(), true);
							Vj.agregarPalabras(rs.getPalabra(), sig.getPalabra());							
							estado.setVj(Vj);
						}else{
							estado.setVj(estadoInstruccion.getInstruccion().getRegRS().getPalabra());
						}
						estado.setQj(new String());
					}
				}
				if(estadoInstruccion.getInstruccion().getRegRT()!=null){
					if(!((qi=estadoInstruccion.getInstruccion().getRegRT().getQi()).equals(new String()))){
						estado.setQk(qi);
						
					}else{
						
						if(estadoInstruccion.getInstruccion().isInstruccionDouble()){
							Instruccion instruccion=estadoInstruccion.getInstruccion();
							Registro rt=instruccion.getRegRT();
							Registro sig=instruccion.relacionaRegF(rt.getSiguiente());							
							PalabraCompuesta Vk=new PalabraCompuesta(rt.getPalabra().getHex(), true);
							Vk.agregarPalabras(rt.getPalabra(), sig.getPalabra());							
							estado.setVk(Vk);
						}else{
							estado.setVk(estadoInstruccion.getInstruccion().getRegRT().getPalabra());
						}
						estado.setQk(new String());
						
					}
				}
				estado.setOcupada(true);
				if(estadoInstruccion.getInstruccion().regModificable()!=null){
					estadoInstruccion.getInstruccion().regModificable().setQi(estacionReserva.getNombre());
					tomasulo.getEstadoRegistros().registrar(estadoInstruccion.getInstruccion().regModificable().toString(),
							estacionReserva.getNombre());
				}
				
			}else if((tomasulo.isLoad(estadoInstruccion.getInstruccion().getTipoER())) ||
					(tomasulo.isStore(estadoInstruccion.getInstruccion().getTipoER()))){
				if(estadoInstruccion.getInstruccion().getRegRS()!=null){
					if(!((qi=estadoInstruccion.getInstruccion().getRegRS().getQi()).equals(new String()))){
						estado.setQj(qi);
					}else{
						
						if(estadoInstruccion.getInstruccion().isInstruccionDouble()){
							Instruccion instruccion=estadoInstruccion.getInstruccion();
							Registro rs=instruccion.getRegRS();
							Registro sig=instruccion.relacionaRegF(rs.getSiguiente());							
							PalabraCompuesta Vj=new PalabraCompuesta(rs.getPalabra().getHex(), true);
							Vj.agregarPalabras(rs.getPalabra(), sig.getPalabra());							
							estado.setVj(Vj);
						}else{
							estado.setVj(estadoInstruccion.getInstruccion().getRegRS().getPalabra());
						}
						estado.setQj(new String());
					}
				}				
				int desplazamiento=((TipoI)estadoInstruccion.getInstruccion()).getDesplazamiento();
				
				
				String estadoA=new String(new Integer(desplazamiento).toString());
				estado.setA(estadoA);
				estado.setOcupada(true);
				if(tomasulo.isLoad(estadoInstruccion.getInstruccion().getTipoER())){
					if(((qi=estadoInstruccion.getInstruccion().getRegRS().getQi()).equals(new String()))){
						tomasulo.getDesambiguarMem().registrarCarga(estadoInstruccion.getInstruccion().getRegRS().getPalabra().sumar(desplazamiento).getHex(),
							new Integer((int)this.pcActual));
					}else{
						tomasulo.getDesambiguarMem().registrarAmbLoad(new Integer((int)this.pcActual));
					}
				}else{
					if(((qi=estadoInstruccion.getInstruccion().getRegRS().getQi()).equals(new String()))){
/**/					tomasulo.getDesambiguarMem().registrarAlmacenamiento(estadoInstruccion.getInstruccion().getRegRS().getPalabra().sumar(desplazamiento).getHex(),
							new Integer((int)this.pcActual));
					}else{
						tomasulo.getDesambiguarMem().registrarAmbStore(new Integer((int)this.pcActual));
					}
					
				}				
				if(tomasulo.isLoad(estadoInstruccion.getInstruccion().getTipoER())){
					estadoInstruccion.getInstruccion().regModificable().setQi(estacionReserva.getNombre());
					tomasulo.getEstadoRegistros().registrar(estadoInstruccion.getInstruccion().regModificable().toString(),
							estacionReserva.getNombre());
/**/			}else{
//					if(!((qi=estadoInstruccion.getInstruccion().getRegRS().getQi()).equals(new String()))){
//						/*Si no es Load es Store*/
//						estado.setQj(estadoInstruccion.getInstruccion().getRegRS().getQi());					
//					}else{
//						
//						
//						if(estadoInstruccion.getInstruccion().isInstruccionDouble()){
//							Instruccion instruccion=estadoInstruccion.getInstruccion();
//							Registro rs=instruccion.getRegRS();
//							Registro sig=instruccion.relacionaRegF(rs.getSiguiente());							
//							PalabraCompuesta Vj=new PalabraCompuesta(rs.getPalabra().getHex(), true);
//							Vj.agregarPalabras(rs.getPalabra(), sig.getPalabra());							
//							estado.setVj(Vj);
//						}else{
//							estado.setVj(estadoInstruccion.getInstruccion().getRegRS().getPalabra());
//						}
//						estado.setQj(new String());
//					}
					if(!((qi=estadoInstruccion.getInstruccion().getRegRT().getQi()).equals(new String()))){
						/*Si no es Load es Store*/
						estado.setQk(estadoInstruccion.getInstruccion().getRegRT().getQi());					
					}else{
						
//						if(estadoInstruccion.getInstruccion().isInstruccionDouble()){
//						Instruccion instruccion=estadoInstruccion.getInstruccion();
//						Registro rt=instruccion.getRegRT();
//						Registro sig=instruccion.relacionaRegF(rt.getSiguiente());							
//						PalabraCompuesta Vj=new PalabraCompuesta(rs.getPalabra().getHex(), true);
//						Vj.agregarPalabras(rs.getPalabra(), sig.getPalabra());							
//						estado.setVj(Vj);
//						}else{
						estado.setVk(estadoInstruccion.getInstruccion().getRegRT().getPalabra());
//						}
						estado.setQk(new String());
					}
				}
				
			}
			
			estado.setCicloModif(this.pcActual);
			estacionReserva.setEstadoInstruccion(estadoInstruccion);
			estacionReserva.setEstado(estado);			
			tomasulo.getEjecucion().anhadirEstacionReserva(estacionReserva);
			tomasulo.setEmision(new Vector<EstadoInstruccion>());
		}else{
			//RIESGO ESTRUCTURAL
			
			tomasulo.getRiesgos().setRiesgosEstructurales(tomasulo.getRiesgos().getRiesgosEstructurales()+1);
			ejecutado=false;
		}
		
		return ejecutado;
	}
	
	private void avanzarEjecucion() throws EjecucionException{
		
		Vector<ERTomasulo> estacionesEjecucion=tomasulo.getEjecucion().getEstacionesReserva();
		
		ERTomasulo estacionReserva;
		EstadoEstacion estado;		
		
		
		for(int i=0;i<estacionesEjecucion.size();i++){
			estacionReserva=estacionesEjecucion.elementAt(i);			
			estado=(EstadoEstacion)estacionReserva.getEstado();			
			
			tomasulo.mirarSalto(estacionReserva.getEstadoInstruccion().getInstruccion());
			if(tomasulo.isOperacionFP(estacionReserva.getTipo())){				
				
				if((estado.getQk().equals(new String())) && (estado.getQj().equals(new String()))){
					
					if(estacionReserva.getPosicionActual()==0){
						if(tomasulo.isFULibre(estacionReserva.getTipo())){
							tomasulo.ocuparFU(estacionReserva.getTipo(), estacionReserva.getNombre());
							if(estacionReserva.getEstadoInstruccion().getInstruccion().regModificable()!=null){
								tomasulo.getEstadoRegistros().registrar(estacionReserva.getEstadoInstruccion().getInstruccion().regModificable().toString(),
										estacionReserva.getNombre());
							}
						}else{
							//RIESGO ESTRUCTURAL						
							tomasulo.getRiesgos().setRiesgosEstructurales(tomasulo.getRiesgos().getRiesgosEstructurales()+1);
						}
					}
					if(tomasulo.esEnFU(estacionReserva.getTipo(), estacionReserva.getNombre())){
						if((estacionReserva.getLatencia()-1)==(estacionReserva.getPosicionActual())){
							EstadoEstacion estadoOcupado=(EstadoEstacion)estacionReserva.getEstado();							
							
							Palabra rsPal=estadoOcupado.getVj();
							Palabra rtPal=estadoOcupado.getVk();
							tomasulo.analizaExcep(((PlanifDinamica)estacionReserva.getEstadoInstruccion().getInstruccion()).ejecutarTomasulo(rsPal, rtPal, estacionReserva.getNombre()));							
							estacionReserva.getEstadoInstruccion().setStatus(Tipos.EJECUCION_TOM);
							tomasulo.getEjecucion().removeEstacionReserva(estacionReserva);
							tomasulo.getEscritura().anhadirEstacionReserva(estacionReserva);
							
						}else{
							
							estacionReserva.setPosicionActual(estacionReserva.getPosicionActual()+1);
							
						}
					}
				}else{
					//RIESGO RAW
					
					tomasulo.getRiesgos().setRiesgosRAW(tomasulo.getRiesgos().getRiesgosRAW()+1);
					
				}
				tomasulo.mirarSalto(estacionReserva.getEstadoInstruccion().getInstruccion());
			}else if((tomasulo.isLoad(estacionReserva.getTipo())) || 
					(tomasulo.isStore(estacionReserva.getTipo()))){
				
				
				if((tomasulo.isLoad(estacionReserva.getTipo()))&&
						(estacionReserva.getLatencia()-1)==(estacionReserva.getPosicionActual())){
					   
					/*Si su posicionActual es diferente de 0 es que tiene q estar en la FU*/
					EstadoEstacion estadoOcupado=(EstadoEstacion)estacionReserva.getEstado();							
					if(!((tomasulo.getDesambiguarMem().ambStoreAnterior(new Integer((int)estadoOcupado.getCicloModif())))
							&& (tomasulo.getDesambiguarMem().almacenamientoAnterior(estadoOcupado.getA(), 
							new Integer((int)estadoOcupado.getCicloModif()))))){
						Palabra rsPal=estadoOcupado.getVj();
						Palabra rtPal=estadoOcupado.getVk();
						tomasulo.analizaExcep(((PlanifDinamica)estacionReserva.getEstadoInstruccion().getInstruccion()).ejecutarTomasulo(rsPal, rtPal, estacionReserva.getNombre()));
					   
						
						tomasulo.getDesambiguarMem().borrarCarga(estadoOcupado.getA(),
								new Integer((int)estadoOcupado.getCicloModif()));
						estado.setA(estacionReserva.getEstadoInstruccion().getInstruccion().regModificable().getPalabra().getHex());
						estacionReserva.getEstadoInstruccion().setStatus(Tipos.EJECUCION_TOM);
						tomasulo.getEjecucion().removeEstacionReserva(estacionReserva);
						tomasulo.getEscritura().anhadirEstacionReserva(estacionReserva);
					}else{
						tomasulo.getRiesgos().setRiesgosRAWMem(tomasulo.getRiesgos().getRiesgosRAWMem()+1);
						//riesgo RAW memoria
					}
				}else{
					if(estado.getQj().equals(new String())){
						
						String direccion;
						
						
						EstadoEstacion estadoGuardado=(EstadoEstacion)estacionReserva.getEstado();
						if(tomasulo.isLoad(estacionReserva.getTipo())){
							direccion=estadoGuardado.getVj().sumar(new Integer(estadoGuardado.getA()).intValue()).getHex();
							if(estacionReserva.getEstadoInstruccion().getInstruccion().getRegRS().getCicloModificado()<estadoGuardado.getCicloModif()){
								estadoGuardado.setVj(estacionReserva.getEstadoInstruccion().getInstruccion().getRegRS().getPalabra());
							}
							if(tomasulo.getDesambiguarMem().esAmbLoad(new Integer((int)estadoGuardado.getCicloModif()))){
								tomasulo.getDesambiguarMem().desregistrarAmbLoad(new Integer((int)estadoGuardado.getCicloModif()));
								tomasulo.getDesambiguarMem().registrarCarga(direccion, new Integer((int)estadoGuardado.getCicloModif()));
							}
						}else{
/**/							direccion=estadoGuardado.getVj().sumar(new Integer(estadoGuardado.getA()).intValue()).getHex();
/**/							if(estacionReserva.getEstadoInstruccion().getInstruccion().getRegRS().getCicloModificado()<estadoGuardado.getCicloModif()){
/**/								estadoGuardado.setVj(estacionReserva.getEstadoInstruccion().getInstruccion().getRegRS().getPalabra());
								}	
								if(tomasulo.getDesambiguarMem().esAmbStore(new Integer((int)estadoGuardado.getCicloModif()))){
									tomasulo.getDesambiguarMem().desregistrarAmbStore(new Integer((int)estadoGuardado.getCicloModif()));
									tomasulo.getDesambiguarMem().registrarAlmacenamiento(direccion, new Integer((int)estadoGuardado.getCicloModif()));
								}
						}
						estacionReserva.setEstado(estadoGuardado);
						
//						if(!tomasulo.getDesambiguarMem().almacenamientoAnterior(direccion, 
//								new Integer((int)estadoGuardado.getCicloModif()))){
							
//							if(!((tomasulo.isStore(estacionReserva.getTipo())) &&
//									(tomasulo.getDesambiguarMem().cargaAnterior(direccion,
//											new Integer((int)estadoGuardado.getCicloModif()))))){							
								if(tomasulo.isFULibre(estacionReserva.getTipo())){
								   
									/*Si hay alguna Fu libre cuando esta estacion de Reserva la 
									 * quiera coger--> entonces esa ER era la 1 de la cola pq
									 * el vector esta ordenado*/
									
									estado.setA(estacionReserva.getEstadoInstruccion().getInstruccion().getRegRS().getPalabra()
											.sumar(new Long(estado.getA()).longValue()).getHex());
									estacionReserva.setPosicionActual(estacionReserva.getPosicionActual()+1);
									if(tomasulo.isStore(estacionReserva.getTipo())){
										estacionReserva.getEstadoInstruccion().setStatus(Tipos.EJECUCION_TOM);
										tomasulo.getEjecucion().removeEstacionReserva(estacionReserva);
										tomasulo.getEscritura().anhadirEstacionReserva(estacionReserva);
									}
//									if(tomasulo.isStore(estacionReserva.getTipo())){
//										EstadoEstacion estadoOcupado=(EstadoEstacion)estacionReserva.getEstado();							
//										
//										Palabra rsPal=estadoOcupado.getVj();
//										Palabra rtPal=estadoOcupado.getVk();
//										tomasulo.analizaExcep(((PlanifDinamica)estacionReserva.getEstadoInstruccion().getInstruccion()).ejecutarTomasulo(rsPal, rtPal,estacionReserva.getNombre()));							
//										estacionReserva.getEstadoInstruccion().setStatus(Tipos.EJECUCION_TOM);
//										tomasulo.getEjecucion().removeEstacionReserva(estacionReserva);
//										tomasulo.getEscritura().anhadirEstacionReserva(estacionReserva);
//										tomasulo.getDesambiguarMem().borrarAlmacenamiento(direccion, new Integer((int)estadoOcupado.getCicloModif()));
//										
//									}
									
								}		
								
								
//							}else{
//								System.out.println("Riesgo WAW");
//								
//							}
//							
//						}else{
//							System.out.println("Riesgo RAW o WAW");
//						}
					}
					
				}
				estacionReserva.setEstado(estado);
				
			}
		}
	}
	
	private void avanzarEscritura() throws EjecucionException{		
		Vector<ERTomasulo> estaciones=tomasulo.getEscritura().getEstacionesReserva();
		ERTomasulo estacionReserva;
		Registro registro=null;
		EstadoEstacion estado;
		if(estaciones.size()>0){
			
			if(estaciones.size()>1){
				
				estacionReserva=getEstacionMayorLatencia(estaciones);
			}else{
				estacionReserva=estaciones.elementAt(0);
			}
			estado=(EstadoEstacion)estacionReserva.getEstado();
			
			if(tomasulo.isStore(estacionReserva.getTipo())){
				
				//if(!((tomasulo.isStore(estacionReserva.getTipo()))&&(!(estado.getQk().equals(new String()))))){
				if(estado.getQk().equals(new String())){
					

					if(((!(tomasulo.getDesambiguarMem().ambStoreAnterior(new Integer((int)estado.getCicloModif()))))
							&& (!(tomasulo.getDesambiguarMem().almacenamientoAnterior(estado.getA(), 
									new Integer((int)estado.getCicloModif())))))){
						
						if(((!(tomasulo.getDesambiguarMem().ambLoadAnterior(new Integer((int)estado.getCicloModif()))))&&
								(!(tomasulo.getDesambiguarMem().cargaAnterior(estado.getA(),
										new Integer((int)estado.getCicloModif())))))){			
							
							
							
							Palabra rsPal=estado.getVj();
							Palabra rtPal=estado.getVk();
							tomasulo.analizaExcep(((PlanifDinamica)estacionReserva.getEstadoInstruccion().getInstruccion()).ejecutarTomasulo(rsPal, rtPal,estacionReserva.getNombre()));							
							//estacionReserva.getEstadoInstruccion().setStatus(Tipos.EJECUCION_TOM);
							//tomasulo.getEjecucion().removeEstacionReserva(estacionReserva);
							//tomasulo.getEscritura().anhadirEstacionReserva(estacionReserva);
							tomasulo.getDesambiguarMem().borrarAlmacenamiento(estado.getA(), new Integer((int)estado.getCicloModif()));
							
							Palabra resultado=((PlanifDinamica)estacionReserva.getEstadoInstruccion().getInstruccion()).WBTomasulo(estacionReserva.getNombre());
							estacionReserva.getEstadoInstruccion().setStatus(Tipos.ESCRITURA_TOM);
							
							tomasulo.getEscritura().removeEstacionReserva(estacionReserva);
							fin.add(estacionReserva);
						}else{
							tomasulo.getRiesgos().setRiesgosWARMem(tomasulo.getRiesgos().getRiesgosWARMem()+1);
							//riesgos WAR memoria
						}
						
					}else{
						tomasulo.getRiesgos().setRiesgosWAWMem(tomasulo.getRiesgos().getRiesgosWAWMem()+1);
						//riesgos WAW memoria
					}
				}
			}else{
				Palabra resultado=((PlanifDinamica)estacionReserva.getEstadoInstruccion().getInstruccion()).WBTomasulo(estacionReserva.getNombre());
				if(estacionReserva.getEstadoInstruccion().getInstruccion().regModificable()!=null){
					registro=estacionReserva.getEstadoInstruccion().getInstruccion().regModificable();
					
					//	registro.removeQi(estacionReserva.getNombre());
					//	tomasulo.getEstadoRegistros().desregistrar(estacionReserva.getEstadoInstruccion().getInstruccion().regModificable().toString());
					
					
					tomasulo.getBus().setActualizacion(estacionReserva.getNombre(),
							registro.toString(), resultado);
					
					
				}
				
				
				estacionReserva.getEstadoInstruccion().setStatus(Tipos.ESCRITURA_TOM);
//				tomasulo.liberarFU(estacionReserva.getTipo());
//				tomasulo.desocupar(estacionReserva);
				
				tomasulo.getEscritura().removeEstacionReserva(estacionReserva);
				fin.add(estacionReserva);
			}
			
		}			
	}
	
	
	private void finalizarCiclo(){
		Registro registro=null;
		EstadoEstacion estadoOcupado;
		ERTomasulo estacionReserva;

		tomasulo.getBus().notificar();
		if(fin.size()>0){

			estacionReserva=fin.elementAt(0);
			if(estacionReserva.getEstadoInstruccion().getInstruccion().regModificable()!=null){
				registro=estacionReserva.getEstadoInstruccion().getInstruccion().regModificable();
				registro.removeQi(estacionReserva.getNombre());
				tomasulo.getEstadoRegistros().desregistrar(estacionReserva.getEstadoInstruccion().getInstruccion().regModificable().toString());
			}
			if(tomasulo.isStore(estacionReserva.getTipo())){
				estadoOcupado=(EstadoEstacion)estacionReserva.getEstado();
				String direccion=estadoOcupado.getA();
				tomasulo.getDesambiguarMem().borrarAlmacenamiento(direccion, new Integer((int)estadoOcupado.getCicloModif()));
			}
			tomasulo.liberarFU(estacionReserva.getTipo());
			tomasulo.desocupar(estacionReserva);
			
			fin.remove(estacionReserva);
		}
	}
	
	private ERTomasulo getEstacionMayorLatencia(Vector<ERTomasulo> estaciones){	
		ERTomasulo fuActual=null;
		ERTomasulo fuMayorLatencia=null;
		int latencia=0;
		EstadoEstacion estado;
		
		for(int i=0;i<estaciones.size();i++){
			fuActual=estaciones.elementAt(i);
			estado=(EstadoEstacion)fuActual.getEstado();
			if(!((tomasulo.isStore(fuActual.getTipo())) && (!(estado.getQk().equals(new String()))))){
				if(fuActual.getLatencia()>latencia){
					fuMayorLatencia=fuActual;
					latencia=fuMayorLatencia.getLatencia();
				}
			}
		}
		if(fuMayorLatencia==null){
			/*Si solo hay almacenamientos que aun no tienen el dato a escribir*/
			fuMayorLatencia=estaciones.elementAt(0);
		}
		return fuMayorLatencia;
	}
	
	
}

