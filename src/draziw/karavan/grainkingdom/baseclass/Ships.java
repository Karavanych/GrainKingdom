package draziw.karavan.grainkingdom.baseclass;

public class Ships {
	
 /*   --����� � ��������
    if isPort then
        p ('^^�������: ', Ships.total, ' ��������.');
        p ('^���������: ', Ships.build, ' ��������.');
        p ('^� ���������� �����: ', Ships.war, ' ��������.');
        p ('^� ��������: ', Ships.sail, ' ��������.');
        p ('^�������/������: ', Ships.port, ' ��������.');
        p ('^����� ��������: ', Ships.now, ' ��������.');
    end*/
	public boolean isPort=false;
	public boolean isPortShow=false;
	
	public int total=0;
	public int build=0;
	public int war=0;
	public int sail=0;
	public int port=0;
	public int now=0;
	
	public Ships() {
		// TODO Auto-generated constructor stub
	}
	
	public String fillReportVars(String ss) {
		return String.format(ss,total,build,war,sail,port,now);		
	}
	
	public void buildPort() {
		isPort=true;
	}
	
	public void svernut() {
        build = 0;
        war = 0;
        port = 0;
        total = now;
	}
}
