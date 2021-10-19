package ensamblador.planificacionDinamica.marcador.actions;

import java.util.Vector;

import ensamblador.datos.Tipos;
import ensamblador.componentes.fus.fusMarcador.EstadoFUMarcador;
import ensamblador.componentes.fus.fusMarcador.FUMarcador;
import ensamblador.estado.EstadoInstruccion;

import ensamblador.instrucciones.Instruccion;
import ensamblador.instrucciones.PlanifDinamica;


import ensamblador.planificacionDinamica.marcador.Marcador;
import ensamblador.registros.Registro;
import ensamblador.util.action.Accion;
import ensamblador.util.excepcion.EjecucionException;
import ensamblador.util.excepcion.FUsOcupadasException;

public class AvanzarCicloAccion extends Accion {
	private long pcActual;
	private Instruccion instruccion;
	private EstadoInstruccion estadoInstruccion;
	private Marcador marcador;
	private Vector<FUMarcador> fin;
	
	public AvanzarCicloAccion(long pcActual, Instruccion instruccion, Marcador marcador) {
		super();
		this.marcador=marcador;
		this.pcActual=pcActual;	
		this.instruccion=instruccion;
		this.fin=new Vector<FUMarcador>();
	}
	
	@Override
	public void ejecutar() throws EjecucionException{

		marcador.setEjecutandoSalto(false);
		marcador.setEjecutarIF(true);
		avanzarEscritura();
		avanzarEjecucion();
		avanzarLectura();
			if(marcador.getInstruccionDecodif()!=null){
		
			if(avanzarEmision(marcador.getInstruccionDecodif(), marcador.getNumInstruccion())){
					
			marcador.setNumInstruccion(marcador.getNumInstruccion()+1);
			marcador.setEjecutarIF(true);

			}else{
				marcador.setEjecutarIF(false);
			}
			
		}
			finalizarCiclo();
			avanzarIF(pcActual, instruccion, marcador.getNumInstruccion());
		marcador.getEstadoInstrucciones().marcarCambios();
		
		
	}
	private void avanzarIF(long pcActual, Instruccion instruccion, int numInstruccion){
		
		
		EstadoInstruccion estadoInstruccion=new EstadoInstruccion(instruccion, pcActual);
		if(!marcador.isPipelineDetenido()){
		if(instruccion!=null){
			
			if(!marcador.getEstadoInstrucciones().isRegistradaInicio(numInstruccion)){

				marcador.getEstadoInstrucciones().registrar(numInstruccion, estadoInstruccion);
				marcador.getEmision().add(estadoInstruccion);
			}
		}
		}
		
	}
	
	
	private boolean avanzarEmision(EstadoInstruccion instr, int numInstruccion){
		boolean ejecutado=false;
		/*Este paso sustituye una parte del paso ID
		 * en la segmentacion simple DLX*/
		/*la emision es EN ORDEN*/
		
		FUMarcador uniFuncional=null;
		EstadoFUMarcador estado=null;
				estadoInstruccion=marcador.getEstadoInstrucciones().getEstado(numInstruccion);
		
			marcador.mirarSalto(estadoInstruccion.getInstruccion());
			if(!(marcador.ocupadaFU(estadoInstruccion.getInstruccion().getTipoFU()))){
				/*Si una unidad funcional esta libre para la instruccion*/
				
				/*y alguna otra instruccion activa tiene el mismo registro*/
				
				if((estadoInstruccion.getInstruccion().regModificable()!=null) && 
						(marcador.getEstadoRegistros().estaRegistrado(estadoInstruccion.getInstruccion().regModificable().toString()))){
					//hay un riesgo WAW

					marcador.getRiesgos().setRiesgosWAW(marcador.getRiesgos().getRiesgosWAW()+1);

					ejecutado=false;
				}else{
					/*Si una unidad funcional esta libre para la instruccion
					 * y ninguna otra instruccion activa tiene el mismo registro destino*/
					
					try{
						uniFuncional=marcador.ocuparFU(estadoInstruccion.getInstruccion().getTipoFU());
				

						estadoInstruccion.setStatus(Tipos.EMISION);
						String fi=null;
						String fj=null;
						String fk=null;
						Registro r;
						
						if((r=estadoInstruccion.getInstruccion().regModificable())!=null){
							fi=r.toString();
						}

						
						if(estadoInstruccion.getInstruccion().getRegRS()!=null){
							fj=estadoInstruccion.getInstruccion().getRegRS().toString();
						}
						if(estadoInstruccion.getInstruccion().getRegRT()!=null){
							fk=estadoInstruccion.getInstruccion().getRegRT().toString();
						}


						EstadoFUMarcador estadoJ=marcador.estaEnFU(fj,Tipos.FI, uniFuncional.toString());
						EstadoFUMarcador estadoK=marcador.estaEnFU(fk,Tipos.FI, uniFuncional.toString());
						String nombreJ=null;
						String nombreK=null;
						if(estadoJ!=null){
							nombreJ=estadoJ.getNombre();						
						}
						if(estadoK!=null){
							nombreK=estadoK.getNombre();
						}
						
						marcador.actualizarFU(uniFuncional, estadoInstruccion.getInstruccion().getOperacion(),
								fi, fj, fk, nombreJ, nombreK);
						
					}catch(FUsOcupadasException e){
						//lanzar InternalErrorException
						//pq acaba de decir q si hay
						//libres y ahora no hay
						System.out.println("InternalErrorException");
					}
					
					estado=(EstadoFUMarcador)uniFuncional.getEstado();
					if(estadoInstruccion.getInstruccion().regModificable()!=null){ //hay instrucc q no tienen reg destino 
						marcador.getEstadoRegistros().registrar(estadoInstruccion.getInstruccion().regModificable().toString(), estado.getNombre());
						estado.setFi(estadoInstruccion.getInstruccion().regModificable().toString());
					}
					uniFuncional.setEstadoInstruccion(estadoInstruccion);
					estado.setOp(estadoInstruccion.getInstruccion().getOperacion());
					

					uniFuncional.setEstado(estado);
					marcador.getLectura().anhadirFU(uniFuncional);
					marcador.setEmision(new Vector<EstadoInstruccion>());
					ejecutado=true;
					
				}
			}else{
				marcador.getRiesgos().setRiesgosEstructurales(marcador.getRiesgos().getRiesgosEstructurales()+1);
		
				
			}

		return ejecutado;
	}
	
	private void avanzarLectura() {
		
		Vector<FUMarcador> fusLect=marcador.getLectura().getFus();
		for(int i=0; i<fusLect.size();i++){
			FUMarcador unidadFuncional=fusLect.elementAt(i);
			//if(fusLect.size()>0){
			//			FUMarcador unidadFuncional=fusLect.elementAt(0);
			if(marcador.busLibre(unidadFuncional.getTipo())){
				EstadoFUMarcador estado=(EstadoFUMarcador)unidadFuncional.getEstado();
				marcador.mirarSalto(unidadFuncional.getEstadoInstruccion().getInstruccion());
				if(estado.isRj() && estado.isRk()){
					estado.setRj(false);
					estado.setRk(false);
					
					marcador.getLectura().removeFU(unidadFuncional);
					
					unidadFuncional.getEstadoInstruccion().setStatus(Tipos.LECTURA);
					unidadFuncional.setEstado(estado);
					marcador.getEjecucion().anhadirFU(unidadFuncional);
					
					
				}else{
					//riesgo RAW
					marcador.getRiesgos().setRiesgosRAW(marcador.getRiesgos().getRiesgosRAW()+1);
					marcador.liberaBus(unidadFuncional.getTipo());	
				}
				marcador.mirarSalto(unidadFuncional.getEstadoInstruccion().getInstruccion());
			}
		}
	}
	
	private void avanzarEjecucion() throws EjecucionException {

		Vector<FUMarcador> fusEjec=marcador.getEjecucion().getFus();
		
		for(int i=0;i<fusEjec.size();i++){
			FUMarcador unidadFuncional=fusEjec.elementAt(i);
			marcador.mirarSalto(unidadFuncional.getEstadoInstruccion().getInstruccion());
			if((unidadFuncional.getLatencia()-1)==unidadFuncional.getPosicionActual()){ //ultima etapa de la FU
				unidadFuncional.setPosicionActual(unidadFuncional.getPosicionActual()+1);
				
				//ejecutar instrucc
				int error=((PlanifDinamica)unidadFuncional.getEstadoInstruccion().getInstruccion()).ejecutarMarcador();
				if(!(error==-1)){
					System.out.println("Error "+error+" en avanzarEscritura en Marcado ACA");
					throw new EjecucionException(error);
				}
				marcador.getEjecucion().removeFU(unidadFuncional);
				marcador.getEscritura().anhadirFU(unidadFuncional);
				unidadFuncional.getEstadoInstruccion().setStatus(Tipos.EJECUCION);

			}else{
				unidadFuncional.setPosicionActual(unidadFuncional.getPosicionActual()+1);

			}
			marcador.mirarSalto(unidadFuncional.getEstadoInstruccion().getInstruccion());
		}		
	}
	
	private void avanzarEscritura() throws EjecucionException {

		Vector<FUMarcador> fusEscr=marcador.getEscritura().getFus();
		boolean fjIgualFi=false;
		boolean fkIgualFi=false;
		
		
		if(fusEscr.size()>0){
			for(int i=0;i<fusEscr.size();i++){
				FUMarcador unidadFuncional=fusEscr.elementAt(i);
				if(marcador.busLibre(unidadFuncional.getTipo())){				
					marcador.mirarSalto(unidadFuncional.getEstadoInstruccion().getInstruccion());
					EstadoFUMarcador estado=(EstadoFUMarcador)unidadFuncional.getEstado();
					EstadoFUMarcador otroEstadoJ=null;
					EstadoFUMarcador otroEstadoK=null;
					boolean isRj=false;
					boolean isRk=false;
					if((otroEstadoJ=marcador.estaEnFU(estado.getFi(), Tipos.FJ, estado.getNombre())) != null){
						fjIgualFi=true;
						isRj=otroEstadoJ.isRj();					
					}
					if((otroEstadoK=marcador.estaEnFU(estado.getFi(), Tipos.FK, estado.getNombre())) != null){
						fkIgualFi=true;
						isRk=otroEstadoK.isRk();					
					}
					
					
					
					if(((!fjIgualFi) || isRj==false)
							&& ((!fkIgualFi) || isRk==false)){
						/*Ejecutar escritura*/
						int error= unidadFuncional.getEstadoInstruccion().getInstruccion().ejecutarWB();
						
						if(!(error==-1)){
							throw new EjecucionException(error);
						}
						//notificar o escribir aqui el resultado (y notificar)
						if(unidadFuncional.getEstadoInstruccion().getInstruccion().isSalto()){
							marcador.setPipelineDetenido(false);
						}
//						marcador.desocupar(unidadFuncional);
						marcador.getEscritura().removeFU(unidadFuncional);
						fin.add(unidadFuncional);
						
						unidadFuncional.getEstadoInstruccion().setStatus(Tipos.ESCRITURA);
						
//						if(unidadFuncional.getEstadoInstruccion().getInstruccion().regModificable()!=null){
//						marcador.getEstadoRegistros().desregistrar(unidadFuncional.getEstadoInstruccion().getInstruccion().regModificable().toString());
//						}
					}else{
						marcador.getRiesgos().setRiesgosWAR(marcador.getRiesgos().getRiesgosWAR()+1);
						
						//hay un riesgo WAR
					}
				}
			}
		}
		
	}
	
	
	private void finalizarCiclo(){
		FUMarcador unidadFuncional;
		marcador.liberarBuses();
		for(int i=0;i<fin.size();i++){
			unidadFuncional=fin.elementAt(i);
			marcador.desocupar(unidadFuncional);
	
			if(unidadFuncional.getEstadoInstruccion().getInstruccion().regModificable()!=null){
				marcador.getEstadoRegistros().desregistrar(unidadFuncional.getEstadoInstruccion().getInstruccion().regModificable().toString());
			}
		}
	}
	
	

	
	
}
