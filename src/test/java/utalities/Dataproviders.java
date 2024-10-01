package utalities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class Dataproviders {

    @DataProvider(name="LoginData")
    public String [][] getData() throws IOException {

       // String path= System.getProperty("user.dir")+"\\loginddt.xlsx";
        String path = "C:\\Users\\SaberS\\IdeaProjects\\SwagLabs\\testdata\\loginddt.xlsx";

        ExcelUtality xutail = new ExcelUtality(path);

        int totalrows = xutail.getRowCount("sheet1");
        int totalcolums = xutail.getCellCount("sheet1",1);

      String logindata [][] = new String[totalrows][totalcolums];

      for(int i =1; i<=totalrows;i++){  //ignore 0 index which is header in excel

          for (int j=0; j<totalcolums ; j++){ // start from first colum

              logindata[i-1][j]= xutail.getCellData("sheet1",i,j);
          }
      }
return logindata;
    }

    //dataprovider 2
}
