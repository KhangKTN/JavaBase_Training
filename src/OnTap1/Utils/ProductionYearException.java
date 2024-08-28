package OnTap1.Utils;

public class ProductionYearException extends Exception{
    public ProductionYearException(){
        super("Production year must be not more than current year!!!");
    }
}
