/**
 *Palabra.java
 *Clase que define como se almacena la informacion
 **/
package ensamblador.datos;



public class Palabra implements ensamblador.datos.Tipos {
    
    private String palAscii;
    private String palHex;
    private long palDec;
    private String palBin; 
    private double palFrac;
    private String palPflot;
    private boolean infinito=false;
    private boolean nan=false;
    
    /**
     *Constructor de la clase palabra
     **/
     public Palabra(String p, boolean hexa) //if hexa=false-->con extension de signo
    {										//if hexa=true-->sin extension de signo (interpretamos direccion)
    	 palHex=p;
	       
    	 if(hexa){
    		 if(palHex.startsWith("0x"))
 	            palHex=palHex.substring(2,palHex.length()); 
	        String zero="00000000";
	        if(palHex.length()!=8)
	            palHex=zero.substring(0,8-palHex.length())+palHex;                    
	        String h="0x"+palHex;
	        Long dec=Long.decode(h);
	       	palDec=dec.parseLong(dec.toString());
	        palBin=Long.toBinaryString(palDec);         
	        while(palBin.length()<32)
	            palBin="0"+palBin;
	        
	    	
	        
    	 }else{    		 
    		palHex="0x"+p;
    		
         	int tamanho=p.length();
            Long dec=Long.decode(palHex);
            
            palDec=dec.parseLong(dec.toString());
            
            palBin=Long.toBinaryString(palDec);
            
             
       
             int tamBin=palBin.length();
             while(tamBin<(tamanho*4)){//completamos los 4bits en caso de numero positivo (ej:0x7=111-->0111)
            	 palBin="0"+palBin;
            	 tamBin++;
             }
            
             String signo=palBin.substring(0,1);
             if(signo.equals(new String("1"))){
            	 palDec=Long.parseLong(palBin.substring(1,palBin.length()),2);
             	palDec=new Long((long)(palDec-Math.pow(2, palBin.length()-1))).longValue();
            
             }
           
    		 for(int i=palBin.length();i<32;i++){
    			 palBin=signo+palBin;
    		 }
    		 palHex=bin2hex(palBin);
    		 
    		
    	 }
    	 
    	 palPflot=palBin;
	     palFrac=float_a_dec(palPflot);
    	 
    }
    
     /**
     *Constructor de la clase palabra
     **/
    
    public Palabra(String p)
    {
        palHex=ascii_a_hex(p);
         String zero="00000000";
        if(palHex.length()!=8)
            palHex=zero.substring(0,8-palHex.length())+palHex;          
        String h="0x"+palHex;
        Long dec=Long.decode(h);
       	palDec=dec.parseLong(dec.toString());
        palBin=Long.toBinaryString(palDec);         
        while(palBin.length()<32)
            palBin="0"+palBin;
        palPflot=palBin;
        palFrac=float_a_dec(palPflot);
        /*Long lo=new Long(palDec);   
        palFrac=lo.doubleValue();
        Double d=new Double(palFrac);
        palPflot=dec_a_float(d,"SP");*/
    }
   
    /**
     *Constructor de la clase palabra
     **/
    
    public Palabra(long p)
    {
        palDec=p;
        palBin=Long.toBinaryString(palDec);
        while(palBin.length()<32)
            palBin="0"+palBin;
        palHex=Long.toHexString(palDec);
        String zero="00000000";
         if(palHex.length()>8)
             palHex=palHex.substring(palHex.length()-8,palHex.length());             
        if(palHex.length()!=8)
            palHex=zero.substring(0,8-palHex.length())+palHex;
        palPflot=palBin;
        palFrac=float_a_dec(palBin);
        /*Long lo=new Long(palDec); 
        palFrac=lo.doubleValue();
        Double d=new Double(palFrac);
        palPflot=dec_a_float(d,"SP");*/
       
    }
    
     /**
     *Constructor de la clase palabra
     **/
    
   public Palabra(double p)
    {
        palFrac=p;
        Double d=new Double(palFrac);
        palPflot=dec_a_float(d,"SP");
        palBin=dec_a_float(d,"SP");        
        palDec=Long.parseLong(palPflot,2);
        palHex=Long.toHexString(palDec);
        String zero="00000000";
         if(palHex.length()>8)
             palHex=palHex.substring(palHex.length()-8,palHex.length());             
        if(palHex.length()!=8)
            palHex=zero.substring(0,8-palHex.length())+palHex;
     }
    
   
   
   
   /**
    *Metodo que pasa de  binario a hexadecimal
    *@param String binario
    *@return String hexadecimal
   **/
   
   public String bin2hex(String  bin){
	   String hexa=new String();
	   
	   String caracter=new String();
	   
	   for(int i=0; i<32;i=i+4){
		   caracter=bin.substring(i, i+4);
		   for(int j=0;j<Tipos.codBin.length;j++){
			   if(caracter.equals(Tipos.codBin[j])){				   
				   hexa=hexa+Tipos.codHex[j];
				   break;
			   }
		   }
	   }
	   return hexa;
	   
   }
    /**
     *Metodo que pasa de formato ascii a hexadecimal
     *@param String ascii
     *@return String hexadecimal
    **/
    
    public String ascii_a_hex(String asci)
    {
        StringBuffer hex=new StringBuffer();
        int i;
        for(int j=asci.length()-1;j>=0;j--)
        {
            for(i=0;i<ascii.length;i++)
            {
                if((asci.substring(j,j+1)).equals(ascii[i]))
                {
                   break;
                }
            }
            
            if(i!=ascii.length)
                hex.append(hexa[i]);                        
        }
        return hex.toString();
    }
    
    /**
     *Metodo que pasa de formato hexadecimal a ascii
     *@param String hexadecimal
     *@param String ascii
     **/
    public String hex_a_ascii(String hex)
    {
        
        StringBuffer asci=new StringBuffer();
        int i;
        for(int j=hex.length()-2;j>=0;j=j-2)
        {
            for(i=0;i<hexa.length;i++)
            {
                if((hex.substring(j,j+2)).equals(hexa[i]))
                    break;
            }
           
            if(i!=hexa.length)
                asci.append(ascii[i]);                        
        }
         return asci.toString();
    }
   
    /**
     *Mtodo que pasa un string de formato decimal a binario cuando no es entero
     *@param String decimal
     *return String binario
     **/
    public String fdec_a_fbin(String decimal){
        String binario;
        Double palabra;
        double pal,pala;
        palabra=Double.valueOf(decimal);
        pal=palabra.doubleValue();    
        binario="0.";
        if(pal==0.0)
            binario=binario+"0";
        while(pal!=0.0){
            pal=pal*2;
            pala=Math.floor(pal);
            if(pala==1.0)
                binario=binario+"1";
            else
                binario=binario+"0";
            pal=pal-pala;
        }
        return binario;
    }
    
    /**
     *Metodo que pasa de formato decimal a binario en punto flotante
     *@param double decimal, String precisin 
     *@return string binario en punto flotante
     **/
    public String dec_a_float(Double dec,String pre){
        int i,posic,posic2,signo,tm,te,exceso,cont=0,n=1;
        long p,s1;
        double s,pa,pala,num,d;
        boolean e=false,negativo=false;
        Long st1;
        Double pal;
        String paBin,palabra,mantisa,exponente,str1,str2="0.",numero;
        StringBuffer flo = new StringBuffer();
        if(pre=="SP"){
            tm=23;
            te=8;
            exceso=127;
        }
        else{
            tm=52;
            te=11;
            exceso=1023;
        }
        palabra=dec.toString();
        if(palabra.startsWith("-")){
            signo=1;
            palabra=palabra.substring(1,palabra.length());
        }
        else
            signo=0;
        for(i=0;i<palabra.length();i++){
            if(palabra.substring(i,i+1).equals("E")){
                e=true;
                break;
            }
        }
        if(e==true){
            if(palabra.substring(i+1,i+2).equals("-"))
                n=-1;
            pal=Double.valueOf(palabra);
            pa=pal.doubleValue();
            while(e==true){
                e=false;
                pa=pa/Math.pow(2,10*n);
                cont=cont+(10*n);
                pal=new Double(pa);
                palabra=pal.toString();
                for(i=0;i<palabra.length();i++){
                    if(palabra.substring(i,i+1).equals("E")){
                        e=true;  
                        break;
                    }
                }
            }
        }
        if(palabra.startsWith("0"))
        {
            paBin=fdec_a_fbin(palabra);
        }
        else{
            posic=palabra.indexOf(".");
            str1=palabra.substring(0,posic);
            str2=str2+palabra.substring(posic+1,palabra.length());
            st1=Long.valueOf(str1);
            s1=st1.longValue();
            str1=Long.toBinaryString(s1);
            str2=fdec_a_fbin(str2);
            str2=str2.substring(2,str2.length());
            paBin=str1+"."+str2;
        }
        if(paBin.equals("0.0")){
            mantisa="0";
            exponente="0";
        }
        else{ 
            d=Math.abs(dec.doubleValue());
            posic=paBin.indexOf("1");
            p=paBin.indexOf(".");
            if((pre=="SP" && d>=1.1755e-38 && d<=3.403e38)||(pre=="DP" && d>=2.225e-308 && d<=(1.7976e308)))
            {
                mantisa=paBin.substring(posic+1,paBin.length());
                if(paBin.startsWith("1")){
                    posic2=mantisa.indexOf(".");
                    mantisa=mantisa.substring(0,posic2)+mantisa.substring(posic2+1,mantisa.length());
                }
                if(paBin.startsWith("1"))
                    p=p-posic-1+exceso+cont;
                else
                    p=p-posic+exceso+cont;
                exponente=Long.toBinaryString(p);
            }
            else        //estamos en el caso de un numero no normalizado
            {   
                posic=paBin.indexOf(".");
                if(cont>0){
                    mantisa="0";
                    if(pre=="SP")
                        exponente="11111111";
                    else
                        exponente="11111111111";
                }
                else
                {
                    if(cont<-(exceso-1)){
                        cont=-exceso+1-cont;
                        mantisa="0";
                        for(i=1;i<cont;i++)
                            mantisa=mantisa+"0";
                        mantisa=mantisa+paBin.substring(posic+1,paBin.length());
                    }
                    else
                    {
                        cont=exceso-1+cont;
                        mantisa=paBin.substring(posic+cont+1,paBin.length());
                        
                    }
                    exponente="0";
                }
            }
        }
        if(mantisa.length()>tm)
            mantisa=mantisa.substring(0,tm);
        while(mantisa.length()<tm)
            mantisa= mantisa+"0";
        if(exponente.length()>te)
            exponente=exponente.substring(exponente.length()-te,exponente.length());
        while(exponente.length()<te)
           exponente="0"+exponente;
        flo.append(signo);
        flo.append(exponente);
        flo.append(mantisa);
        return flo.toString();
    }
    
    /**
     *Metodo que pasa de formato decimal a binario en punto flotante
     *@param String binario en punto flotante
     *@return Double decimal
     **/
    public double float_a_dec(String flo){
        int tm,te,tp,exceso,ma,s;
        int i=0,j=0,k=-1,n=1,posic; 
        double m=0,man;
        long e,mant;
        String mantisa,exponente,signo,digito;
        if(flo.length()==32){
            tm=23;
            te=8;
            tp=32;
            exceso=127;
        }
        else{
            tm=52;
            te=11;
            tp=64;
            exceso=1023;
        }
        signo=flo.substring(0,1);  
        exponente=flo.substring(1,te+1);
        mantisa=flo.substring(te+1,tp);
        s=Integer.valueOf(signo).intValue();
        e=Long.parseLong(exponente,2);
        mant=Long.parseLong(mantisa,2);
        posic=mantisa.lastIndexOf("1");
        mantisa=mantisa.substring(0,posic+1);
        mantisa="0."+mantisa;
        posic=mantisa.indexOf(".");
        for(i=posic+1;i<mantisa.length();i++)
        {
            digito=mantisa.substring(i,i+1);
            ma=Integer.valueOf(digito).intValue();
            man=ma*Math.pow(2,k);
            m=m+man;
            k--;;
        }
        //comprobaciones para saber si realmente es un num en formato IEEE
        if(e==0)
            if(mant==0)
                return 0;                                               //cero
            else
                palFrac=Math.pow(-1,s)*m*Math.pow(2,-(exceso-1));;      //numero no normalizado
        if(e==(exceso*2+1))
            if(mant==0)
            {                                                           //+ o - infinito    
                if(s==0)
                    palFrac=15*1e308;                                        
                else
                    palFrac=-15*1e308;
            }
            else
            {                                                           //Nan
                palFrac=Math.pow(-1,s)*(1+m)*Math.pow(2,(e-exceso));
            }
         if(e>=1 && e<=(exceso*2))
            palFrac=Math.pow(-1,s)*(1+m)*Math.pow(2,(e-exceso));        //numero en coma flotante  
        
        return palFrac;
    }
    
    /**
     *Metodo que devuelve el valor de la palabra en ascii
     *@param sin parametros
     *@return String ascii
     **/
    public String getAscii()
    {
        return hex_a_ascii(palHex);
    }
    
    
    /**
     *Metodo incrementa el valor actual con el pasado por parametro
     *@param long sumando
     *@return Palabra palabra
     **/
    public Palabra sumar(long sumando)
    {
        long sum= this.palDec+sumando;
        return new Palabra(sum);
    }

    /* (non-Javadoc)
	 * @see ensamblador.datos.PalabraStandard#getDec()
	 */
    public long getDec()
    {
        return this.palDec;
    }
    
    /* (non-Javadoc)
	 * @see ensamblador.datos.PalabraStandard#getDec()
	 */
    public long getDireccion()
    {
    	 String h="0x"+palHex;
	     Long dec=Long.decode(h);
	     return dec.parseLong(dec.toString());
        
    }
        /* (non-Javadoc)
		 * @see ensamblador.datos.PalabraStandard#getHex()
		 */
    public String getHex()
    {                
        return "0x"+this.palHex;
    }
    
     /* (non-Javadoc)
	 * @see ensamblador.datos.PalabraStandard#getBin()
	 */
    public String getBin()
    {
        return palBin;
    }
    
    
    /* (non-Javadoc)
	 * @see ensamblador.datos.PalabraStandard#getFloat()
	 */
    public String getFloat()
    {
    	
    	 
    //	 return this.dec_a_float(palFrac, "SP");
       return palPflot;
    }
    
    
    /* (non-Javadoc)
	 * @see ensamblador.datos.PalabraStandard#getFrac()
	 */
    public double getFrac()
    {
        return palFrac;
    }
    
    
    /**
     *Metodo que modifica el valor actual por el pasado por parametro
     *@param long modificacion
     *@return void
     **/
    public void modificar(long modificacion)
    {
        this.palDec=modificacion;
        this.palBin=Long.toBinaryString(palDec);
        this.palHex=Long.toHexString(palDec);   
           String zero="00000000";
        if(this.palHex.length()!=8)
            palHex=zero.substring(0,8-palHex.length())+palHex;    
    }
    
     /**
     *Metodo que modifica el valor actual por el pasado por parametro
     *@param long modificacion
     *@return void
     **/
    public void modificar(String modificacion)
    {
        this.palHex=modificacion;
           String zero="00000000";
        if(this.palHex.length()!=8)
            palHex=zero.substring(0,8-palHex.length())+palHex;    
        Long dec=Long.decode(palHex);
        this.palDec=dec.parseLong(dec.toString());      
        this.palBin=Long.toBinaryString(palDec);               
    }      
    
    
    /**
     *Metodo que devuelve el bit de signo del numero almacenado 
     *@param sin parametros
     *@return int valor del bit de signo
     **/
    public int getBit(){
        int num;
        String str;
        Integer in;
        str=this.getFloat();
        str=str.substring(0,1);
        num=Integer.valueOf(str).intValue();
        return num;
    }
    
    /**
     *Metodo que devuelve la palabra pero con el nuevo bit de signo
     *@param int bit de signo de la nueva palabra
     *@return Palabra con el nuevo bit ya modificado
     **/
    public Palabra setBit(int i){
        Palabra pal=new Palabra("pal");
        String s,str;
        Integer in;
        str=this.getFloat();
        in=new Integer(i);
        s=in.toString();
        str=s+str.substring(1,str.length());
        pal=new Palabra(pal.float_a_dec(str));
        return pal;
    }
    
    
    
    public String getBit(int posicion){
    	if(posicion<palBin.length()){
    		int posPalabra=palBin.length()-posicion;
    		return palBin.substring(posPalabra-1, posPalabra);
    	}
    	return null;
    }
    
    public void setBit(int posicion, String caracter){
    	
    	String palBinAux=palBin;
    	
    	
    	if(posicion<palBin.length()){    		
    		int posPalabra=palBin.length()-posicion;
    	
    		if(caracter.length()>1){
    			caracter=caracter.substring(caracter.length()-1,caracter.length()-1);    		
    		}
    		palBin=palBinAux.substring(0, posPalabra-1)+caracter;
    		
    		
    		if(palBin.length()<palBinAux.length()){
    			palBin+=palBinAux.substring(posPalabra, palBinAux.length());    			    			
    		}
    		
    	
    		//regenera la palabra en el resto de formatos a partir del binario
    		String hex=new String();
    		String car=new String();
    		long valor=0;
    		for(int i=0;i<palBin.length();i++){
    			posPalabra=palBin.length()-1-i;
    			car=palBin.substring(posPalabra, posPalabra+1);
    			
    			valor+=Long.parseLong(car)*Math.pow(2, i);
    		}
    		
    		palDec=valor;
    		 palHex=Long.toHexString(palDec);
    	        String zero="00000000";
    	         if(palHex.length()>8)
    	             palHex=palHex.substring(palHex.length()-8,palHex.length());             
    	        if(palHex.length()!=8)
    	            palHex=zero.substring(0,8-palHex.length())+palHex;
    	        palPflot=palBin;
    	        palFrac=float_a_dec(palBin);
    	        
    	        
    	}
    }
    
    
    public void agregarPalabras(Palabra p1, Palabra p2){}
	public Palabra obtenerPalabra(int posicion){
		if(posicion==0){
			return this;
		}else{
			return null;
		}
	}
}
