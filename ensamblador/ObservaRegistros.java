/**
 *ObservaRegistro.java
 *Clase que controla la actualizacin de los registros
 **/
package ensamblador;

import java.util.Observable;
import java.util.Observer;

import ensamblador.registros.A0;
import ensamblador.registros.A1;
import ensamblador.registros.A2;
import ensamblador.registros.A3;
import ensamblador.registros.Cause;
import ensamblador.registros.EPC;
import ensamblador.registros.F0;
import ensamblador.registros.F1;
import ensamblador.registros.F10;
import ensamblador.registros.F11;
import ensamblador.registros.F12;
import ensamblador.registros.F13;
import ensamblador.registros.F14;
import ensamblador.registros.F15;
import ensamblador.registros.F16;
import ensamblador.registros.F17;
import ensamblador.registros.F18;
import ensamblador.registros.F19;
import ensamblador.registros.F2;
import ensamblador.registros.F20;
import ensamblador.registros.F21;
import ensamblador.registros.F22;
import ensamblador.registros.F23;
import ensamblador.registros.F24;
import ensamblador.registros.F25;
import ensamblador.registros.F26;
import ensamblador.registros.F27;
import ensamblador.registros.F28;
import ensamblador.registros.F29;
import ensamblador.registros.F3;
import ensamblador.registros.F30;
import ensamblador.registros.F31;
import ensamblador.registros.F4;
import ensamblador.registros.F5;
import ensamblador.registros.F6;
import ensamblador.registros.F7;
import ensamblador.registros.F8;
import ensamblador.registros.F9;
import ensamblador.registros.Hi;
import ensamblador.registros.K0;
import ensamblador.registros.K1;
import ensamblador.registros.Lo;
import ensamblador.registros.Pc;
import ensamblador.registros.Ra;
import ensamblador.registros.Registro;
import ensamblador.registros.S0;
import ensamblador.registros.S1;
import ensamblador.registros.S2;
import ensamblador.registros.S3;
import ensamblador.registros.S4;
import ensamblador.registros.S5;
import ensamblador.registros.S6;
import ensamblador.registros.S7;
import ensamblador.registros.FP;
import ensamblador.registros.T9;
import ensamblador.registros.Sp;
import ensamblador.registros.Status;
import ensamblador.registros.T0;
import ensamblador.registros.T1;
import ensamblador.registros.T2;
import ensamblador.registros.T3;
import ensamblador.registros.T4;
import ensamblador.registros.T5;
import ensamblador.registros.T6;
import ensamblador.registros.T7;
import ensamblador.registros.T8;
import ensamblador.registros.V0;
import ensamblador.registros.V1;
import ensamblador.registros.Zero;
public class ObservaRegistros extends Observable implements Observer {
    /**
     *Constructor de ObservaRegistros
     **/
    public ObservaRegistros() {
        Zero $zero=Zero.getRegistro();
        $zero.addObserver(this);
        V0 $v0=V0.getRegistro();
        $v0.addObserver(this);
        V1 $v1=V1.getRegistro();
        $v1.addObserver(this);
        A0 $a0=A0.getRegistro();
        $a0.addObserver(this);
        A1 $a1=A1.getRegistro();
        $a1.addObserver(this);
        A2 $a2=A2.getRegistro();
        $a2.addObserver(this);
        A3 $a3=A3.getRegistro();
        $a3.addObserver(this);
        T0 $t0=T0.getRegistro();
        $t0.addObserver(this);
        T1 $t1=T1.getRegistro();
        $t1.addObserver(this);
        T2 $t2=T2.getRegistro();
        $t2.addObserver(this);
        T3 $t3=T3.getRegistro();
        $t3.addObserver(this);
        T4 $t4=T4.getRegistro();
        $t4.addObserver(this);
        T5 $t5=T5.getRegistro();
        $t5.addObserver(this);
        T6 $t6=T6.getRegistro();
        $t6.addObserver(this);
        T7 $t7=T7.getRegistro();
        $t7.addObserver(this);
        T8 $t8=T8.getRegistro();
        $t8.addObserver(this);
        S0 $s0=S0.getRegistro();
        $s0.addObserver(this);
        S1 $s1=S1.getRegistro();
        $s1.addObserver(this);
        S2 $s2=S2.getRegistro();
        $s2.addObserver(this);
        S3 $s3=S3.getRegistro();
        $s3.addObserver(this);
        S4 $s4=S4.getRegistro();
        $s4.addObserver(this);
        S5 $s5=S5.getRegistro();
        $s5.addObserver(this);
        S6 $s6=S6.getRegistro();
        $s6.addObserver(this);
        S7 $s7=S7.getRegistro();
        $s7.addObserver(this);
        FP $fp=FP.getRegistro();
        $fp.addObserver(this);
        T9 $t9=T9.getRegistro();
        $t9.addObserver(this);
        K0 $k0=K0.getRegistro();
        $k0.addObserver(this);
        K1 $k1=K1.getRegistro();
        $k1.addObserver(this);
        Sp sp=Sp.getRegistro();
        sp.addObserver(this);
        Ra ra=Ra.getRegistro();
        ra.addObserver(this);
        
        Pc pc=Pc.getRegistro();
        pc.addObserver(this);           
        Hi hi=Hi.getRegistro();
        hi.addObserver(this);
        Lo lo=Lo.getRegistro();
        lo.addObserver(this);
        Status status=Status.getRegistro();
        status.addObserver(this);
        EPC epc=EPC.getRegistro();
        epc.addObserver(this);
        Cause cause=Cause.getRegistro();
        cause.addObserver(this);
        
        F0 $f0=F0.getRegistro();
        $f0.addObserver(this);
        F1 $f1=F1.getRegistro();
        $f1.addObserver(this);
        F2 $f2=F2.getRegistro();
        $f2.addObserver(this);
        F3 $f3=F3.getRegistro();
        $f3.addObserver(this);
        F4 $f4=F4.getRegistro();
        $f4.addObserver(this);
        F5 $f5=F5.getRegistro();
        $f5.addObserver(this);
        F6 $f6=F6.getRegistro();
        $f6.addObserver(this);
        F7 $f7=F7.getRegistro();
        $f7.addObserver(this);
        F8 $f8=F8.getRegistro();
        $f8.addObserver(this);
        F9 $f9=F9.getRegistro();
        $f9.addObserver(this);
        F10 $f10=F10.getRegistro();
        $f10.addObserver(this);
        F11 $f11=F11.getRegistro();
        $f11.addObserver(this);
        F12 $f12=F12.getRegistro();
        $f12.addObserver(this);
        F13 $f13=F13.getRegistro();
        $f13.addObserver(this);
        F14 $f14=F14.getRegistro();
        $f14.addObserver(this);
        F15 $f15=F15.getRegistro();
        $f15.addObserver(this);
        F16 $f16=F16.getRegistro();
        $f16.addObserver(this);
        F17 $f17=F17.getRegistro();
        $f17.addObserver(this);
        F18 $f18=F18.getRegistro();
        $f18.addObserver(this);
        F19 $f19=F19.getRegistro();
        $f19.addObserver(this);
        F20 $f20=F20.getRegistro();
        $f20.addObserver(this);
        F21 $f21=F21.getRegistro();
        $f21.addObserver(this);
        F22 $f22=F22.getRegistro();
        $f22.addObserver(this);
        F23 $f23=F23.getRegistro();
        $f23.addObserver(this);
        F24 $f24=F24.getRegistro();
        $f24.addObserver(this);
        F25 $f25=F25.getRegistro();
        $f25.addObserver(this);
        F26 $f26=F26.getRegistro();
        $f26.addObserver(this);
        F27 $f27=F27.getRegistro();
        $f27.addObserver(this);
        F28 $f28=F28.getRegistro();
        $f28.addObserver(this);
        F29 $f29=F29.getRegistro();
        $f29.addObserver(this);
        F30 $f30=F30.getRegistro();
        $f30.addObserver(this);
        F31 $f31=F31.getRegistro();
        $f31.addObserver(this);
        
    }
    
    /**
     *Metodo que se encarga de notificar las actualizaciones
     **/
    
    public void update(Observable registro, Object valor)
    {        
        setChanged();
        
        notifyObservers(registro);
        
    }
}
