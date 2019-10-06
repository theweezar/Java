/**
 * regex
 */
public class regex {

  public static void Split(){
    String name = "Hoang Phan Minh Duc";
    String[] arr_name = name.split(" ");
    for(int i=0;i<arr_name.length;i++){
      System.out.println(arr_name[i]);
    }
  }
  public static void Regex1(){
    String json = "{'name':'Hoang Phan Minh Duc','age':20,'email':'hpmduc3010@gmail.com','email_school':'n17dcat018@student.ptithcm.edu.vn','cmnd':'025939049','sdt':'0935723714'}";
    String[] json_arr = json.split("[a-z]");
    for(int i=0;i<json_arr.length;i++){
      System.out.println(json_arr[i]);
    }
  }
  public static void main(String[] args) {
    // Split();
    Regex1();
  }
}