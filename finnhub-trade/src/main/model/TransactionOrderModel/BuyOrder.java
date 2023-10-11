import java.util.TreeMap;

import lombok.Data;

@Data
public class BuyOrder {
  
  public static TreeMap<BuyOrder, OrderInfo> buyList = new TreeMap<>();

  private int id;

  public BuyOrder(){
    id++;
  }

  public static void main(String[] args) {
    BuyOrder buy = new BuyOrder();
    System.out.println(buy.getId());
  }
}
