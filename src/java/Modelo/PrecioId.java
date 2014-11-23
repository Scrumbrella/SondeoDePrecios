package Modelo;
// Generated 11-23-2014 10:06:42 AM by Hibernate Tools 3.6.0



/**
 * PrecioId generated by hbm2java
 */
public class PrecioId  implements java.io.Serializable {


     private short idestablecimiento;
     private long idproducto;

    public PrecioId() {
    }

    public PrecioId(short idestablecimiento, long idproducto) {
       this.idestablecimiento = idestablecimiento;
       this.idproducto = idproducto;
    }
   
    public short getIdestablecimiento() {
        return this.idestablecimiento;
    }
    
    public void setIdestablecimiento(short idestablecimiento) {
        this.idestablecimiento = idestablecimiento;
    }
    public long getIdproducto() {
        return this.idproducto;
    }
    
    public void setIdproducto(long idproducto) {
        this.idproducto = idproducto;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PrecioId) ) return false;
		 PrecioId castOther = ( PrecioId ) other; 
         
		 return (this.getIdestablecimiento()==castOther.getIdestablecimiento())
 && (this.getIdproducto()==castOther.getIdproducto());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdestablecimiento();
         result = 37 * result + (int) this.getIdproducto();
         return result;
   }   


}

