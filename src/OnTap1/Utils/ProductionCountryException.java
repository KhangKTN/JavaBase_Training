package OnTap1.Utils;

public class ProductionCountryException extends Exception{
    public ProductionCountryException(){
        super("Production country must be in the list of Korea, Japan, Vietnam, Singapore, Sweden and Thailand!!!");
    }
}
