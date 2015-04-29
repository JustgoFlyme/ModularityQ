import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import Jama.Matrix;


public class Q {
    public static void main(String []args){
    	Matrix Adjacent=null;
    	Matrix moc=null;
    	try{
			FileReader in=new FileReader("./adjacent.txt");
			FileReader in1=new FileReader("./moc.txt");
			BufferedReader bReader=new BufferedReader(in);
			BufferedReader bReader1=new BufferedReader(in1);
			Adjacent=Matrix.read(bReader);
			moc=Matrix.read(bReader1);
			}catch(Exception ex){
				ex.printStackTrace();
			}
    	System.out.print("��������Ч����"+EQ(Adjacent,moc));
    	
    }
	public static double EQ(Matrix Adjacent,Matrix MOC){
		int nodeNum=0;
		int vclusterNum=0;
		int wclusterNum=0;
		int vdegree=0;
		int wdegree=0;
		double sum=0;
		int edgeNum=0;
		ArrayList al=new ArrayList();
		for(int x=0;x<Adjacent.getRowDimension();x++){
			for(int y=0;y<Adjacent.getColumnDimension();y++){
				edgeNum+=Adjacent.get(x,y);
			}
		}
		edgeNum/=2;
		for(int i=0;i<MOC.getColumnDimension();i++){
			//���������ڲ��ڵ�
			for(int j=0;j<MOC.getRowDimension();j++){
				if(MOC.get(j, i)==1){
					nodeNum+=1;
					al.add(j);
				}	
			}
			for(int k=0;k<nodeNum;k++){
				for(int l=0;l<nodeNum;l++){
					//����ڵ�v��w�����������ĸ���
					for(int m=0;m<MOC.getColumnDimension();m++){
						vclusterNum+=(int)MOC.get((int) al.get(k), m);
						wclusterNum+=(int)MOC.get((int)al.get(l), m);
					}
					//����ڵ�v��w�Ķ�
					for(int n=0;n<Adjacent.getColumnDimension();n++){
						vdegree+=Adjacent.get((int)al.get(k),n);
						wdegree+=Adjacent.get((int)al.get(l),n);
					}
					sum+=(1/((double)vclusterNum*wclusterNum))*(Adjacent.get((int) al.get(k), (int)al.get(l))-vdegree*wdegree/((double)2*edgeNum));
					vclusterNum=0;wclusterNum=0;vdegree=0;wdegree=0;
					System.out.println("��ǰֵΪ��"+sum);
				}
			}
			nodeNum=0;
		}
		return sum/(2*edgeNum);
	}
}
 