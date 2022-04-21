package jbank.data;

public record stocks2 (String name, int value) {

public static void main(String[] args) {
   stocks2 stock = new stocks2("test", 100);
   System.out.println(stock.name+stock.value);
   
}

}