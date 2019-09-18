package btvnt3;

/**
 * DANHSACH
 */
public class DANHSACH {
  private HOCSINH[] ds;
  DANHSACH(int sl){
    ds = new HOCSINH[sl];
    for(int i=0;i<sl;i++){
      ds[i] = new HOCSINH();
    }
    inDS();
  }
  public void inDS(){
    for(int i=0;i<ds.length;i++){
      ds[i].output();
    }
  }
  public void sapxeptheodtbgiamdan(){
    HOCSINH tmp;
    for(int i=0;i<ds.length;i++){
      for(int j=i;j<ds.length;j++){
        if (ds[i].getDTB() < ds[j].getDTB()){
          tmp = ds[i];
          ds[i] = ds[j];
          ds[j] = tmp;
        }
      }
    }
  }
}