
package ensamblador.datos;


public interface Tipos {    
    char[] registros={'z', 'a', 'v', 't', 's', 'r', 'f', 'k'};
    int REG_Z=0;
    int REG_A=1;
    int REG_V=2;
    int REG_T=3;
    int REG_S=4;
    int REG_R=5;
    int REG_F=6;
    int REG_K=7;
    int R_BADVADDR=8;
    int R_STATUS=12;
    int R_CAUSE=13;
    int R_EPC=14;
    Palabra INICIO_PILA=new Palabra("6ffffffc",true);
    Palabra INICIO_PC=new Palabra("00400000",true);
    Palabra INICIO_MEMORIA=new Palabra("10010000",true);
    Palabra INICIO_ES=new Palabra("ffff0000",true);
    Palabra FIN_ES=new Palabra("ffff000c",true);
    Palabra ReceiverControl=new Palabra("ffff0000", true);
    Palabra ReceiverData=new Palabra("ffff0004", true);
    Palabra TransmitterControl=new Palabra("ffff0008",true);
    Palabra TransmitterData=new Palabra("ffff000c",true);
    Palabra TOPE_POS=new Palabra("7fffffff",true);
    Palabra TOPE_NEG=new Palabra(-2147483648);
    String [] regEspecial={"zero", "at", "k0", "k1", "gp"};
    
    int TEXT=0;
    int DATA=1;
    int TEXT0X=2;
    
    String registro[]={"$zero","$at","$v0","$v1","$a0","$a1","$a2","$a3",
    "$t0","$t1","$t2","$t3","$t4","$t5","$t6","$t7","$s0","$s1","$s2","$s3",
    "$s4","$s5","$s6","$s7","$t8","$s9","$k0","$k1","$gp","$sp","$s8","$ra"};
    
    String regFlotante[]={"$f0","$f1","$f2","$f3","$f4","$f5","$f6","$f7",
    "$f8","$f9","$f10","$f11","$f12","$f13","$f14","$f15","$f16","$f17","$f18",
    "$f19","$f20","$f21","$f22","$f23","$f24","$f25","$f26","$f27","$f28",
    "$f29","$f30","$f31"};
    
    int REG_ZERO=0;
    int REG_AT=1;
    int REG_V0=2;
    int REG_V1=3;
    int REG_A0=4;
    int REG_A1=5;
    int REG_A2=6;
    int REG_A3=7;
    int REG_T0=8;
    int REG_T1=9;
    int REG_T2=10;
    int REG_T3=11;
    int REG_T4=12;
    int REG_T5=13;
    int REG_T6=14;
    int REG_T7=15;
    int REG_S0=16;
    int REG_S1=17;
    int REG_S2=18;
    int REG_S3=19;
    int REG_S4=20;
    int REG_S5=21;
    int REG_S6=22;
    int REG_S7=23;
    int REG_T8=24;
    int REG_S9=25;
    int REG_K0=26;
    int REG_K1=27;
    int REG_GP=28;
    int REG_SP=29;
    int REG_S8=30;
    int REG_RA=31;
    
    int REG_PC=32;
    int REG_HI=33;
    int REG_LO=34;
    int REG_STATUS=35;
    int REG_CAUSE=36;
    int REG_EPC=37;
        
    int REG_F0=0;
    int REG_F1=1;
    int REG_F2=2;
    int REG_F3=3;
    int REG_F4=4;
    int REG_F5=5;
    int REG_F6=6;
    int REG_F7=7;
    int REG_F8=8;
    int REG_F9=9;
    int REG_F10=10;
    int REG_F11=11;
    int REG_F12=12;
    int REG_F13=13;
    int REG_F14=14;
    int REG_F15=15;
    int REG_F16=16;
    int REG_F17=17;
    int REG_F18=18;
    int REG_F19=19;
    int REG_F20=20;
    int REG_F21=21;
    int REG_F22=22;
    int REG_F23=23;
    int REG_F24=24;
    int REG_F25=25;
    int REG_F26=26;
    int REG_F27=27;
    int REG_F28=28;
    int REG_F29=29;
    int REG_F30=30;
    int REG_F31=31;
    
    String instrucciones[]={"add","addi","sub","lui","slt","slti","and","andi",
    "or","ori","beq","bne","j","lw","sw","lb","sb","la","jal","jr","mult","div",
    "mfhi","mflo","nop","syscall","add.s","sub.s","mul.s","div.s","add.d","sub.d",
    "mul.d","div.d","bc1t","bc1f","abs.s","abs.d","neg.s","neg.d","mov.s","mov.d",
    "c.eq.s","c.eq.d","c.le.s","c.le.d","c.lt.s","c.lt.d","cvt.d.s","cvt.d.w",
    "cvt.s.d","cvt.s.w","cvt.w.d","cvt.w.s","mtc1","mfc1","lwc1","swc1", "li", 
    "mfc0", "rfe", "sll", "srl", "sra"};
    
    int ADD=0;
    int ADDI=1;
    int SUB=2;
    int LUI=3;
    int SLT=4;
    int SLTI=5;
    int AND=6;
    int ANDI=7;
    int OR=8;
    int ORI=9;
    int BEQ=10;
    int BNE=11;
    int INS_J=12;
    int LW=13;
    int SW=14;
    int LB=15;
    int SB=16;
    int LA=17;
    int JAL=18;
    int JR=19;
    int MULT=20;
    int DIV=21;
    int MFHI=22;
    int MFLO=23;
    int NOP=24;
    int SYSCALL=25;
    int ADDS=26;
    int SUBS=27;
    int MULS=28;
    int DIVS=29;
    int ADDD=30;
    int SUBD=31;
    int MULD=32;
    int DIVD=33;
    int BC1T=34;
    int BC1F=35;
    int ABSS=36;
    int ABSD=37;
    int NEGS=38;
    int NEGD=39;
    int MOVS=40;
    int MOVD=41;
    int CEQS=42;
    int CEQD=43;
    int CLES=44;
    int CLED=45;
    int CLTS=46;
    int CLTD=47;
    int CVTDS=48;
    int CVTDW=49;
    int CVTSD=50;
    int CVTSW=51;
    int CVTWD=52;
    int CVTWS=53;
    int MTC1=54;
    int MFC1=55;
    int LWC1=56;
    int SWC1=57;
    int LI=58;
    int MFC0=59;
    int RFE=60;
    int SLL=61;
    int SRL=62;
    int SRA=63;
         
    String datos[]={".ascii",".asciiz",".word",".space",".float",".double"};
    
    int ASCII=0;   
    int ASCIIZ=1;
    int WORD=2;
    int SPACE=3;
    int FLOTANTE=4;
    int DOBLE=5;
    
    
    String []codHex={"0", "1","2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    String []codBin={"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111",
    				 "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111" };
    String []ascii={"\0"," ", "!", "\"", "#", "$", "%", "&", "`", "(", ")", "*", "+", ",", "-", ".", "/",    		
    "0","1","2","3","4","5","6","7","8","9",":",";","<","=",">","?",
    "@","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O",
    "P","Q","R","S","T","U","V","W","X","Y","Z","[","","]","^","-",
    "'","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o",
    "p","q","r","s","t","u","v","w","x","y","z","{","_","}", "~" };
    
    String []hexa={"00","20","21","22","23","24","25","26","27","28","29","2a","2b","2c","2d","2e","2f",
    "30","31","32","33","34","35","36","37","38","39","3a","3b","3c","3d","3e","3f",
    "40","41","42","43","44","45","46","47","48","49","4a","4b","4c","4d","4e","4f",
    "50","51","52","53","54","55","56","57","58","59","5a","5b","5c","5d","5e","5f",
    "60","61","62","63","64","65","66","67","68","69","6a","6b","6c","6d","6e","6f",
    "70","71","72","73","74","75","76","77","78","79","7a","7b","7c","7d","7e"};
    int FI=0;
    int FJ=1;
    int FK=2;

    int UF_NO_SEGM=0;
    int UF_SEGM=1;


    int INTEGER_FU=0;
    int FP_ADD_FU=1;
    int FP_MULT_FU=2;
    int FP_DIV_FU=3;
    
    int TIPOS_FU=4;

    int INTEGER_ER=0;
    int FP_ADD_ER=1;
    int FP_MULT_ER=2;
    int FP_DIV_ER=3;
    int LOAD_ER=4;
    int STORE_ER=5;
    
    int TIPOS_ER=6;



    
    int MARCADOR=0;
    int TOMASULO=1;

    int INICIO=-1;
    int EMISION=0;
    int LECTURA=1;
    int EJECUCION=2;
    int ESCRITURA=3;
    
    int EMISION_TOM=0;
    int EJECUCION_TOM=1;
    int ESCRITURA_TOM=2;
    
    int ETAPA_IF=0;
    int ETAPA_ID=1;
    int ETAPA_EX=2;
    int ETAPA_MEM=3;
    int ETAPA_WB=4;
    
    int SALTO_RETARDAD0=0;
    int SALTO_FIJO=1;
    
    int ANT_RS_MEM=0;
    int ANT_RT_MEM=1;
    int ANT_RS_WB=2;
    int ANT_RT_WB=3;
    
    int STRING=0;
    int INTEGER=1;
    int FLOAT=2;
    int DOUBLE=3;
    
    int TRANSMITER_CONTROL=2;
}
