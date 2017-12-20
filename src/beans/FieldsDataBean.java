package beans;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import service.PrologService;

//import data.KakuroTableData;

@ManagedBean(name="fields")
//@SessionScoped
public class FieldsDataBean {

	private final static String BLOCKED_FIELD = "<td class='black'>&#160;</td>";
	private HashMap<String, String> resultMap=new HashMap<>();
	private String solved[]=new String[12];
	private int solvedCounter=0;
	
	private Boolean result = null;
	
	public FieldsDataBean() {
	}

	public void checkData(ActionEvent event) {
		Map<String, Object> params = event.getComponent().getAttributes();
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for (String character : PrologService.KEYS_ARRAY) {
			String value = (String) params.get(character);
			map.put(character, Integer.valueOf((value == null || value.isEmpty()) ? "0" : value));
		}
		
		result = (new PrologService()).compareResults(map);
		System.out.println(String.valueOf(result));
	}

	public void solvePuzzle() {
		Map<String, Integer> solution = (new PrologService()).getSolution();

		solution.forEach((key, value) -> {
			solved[solvedCounter++] = String.valueOf(value);
		});
	}
	
	public String getInfoField(String rowInfo, String columnInfo) {
		String result = "<h:outputText styleClass='infocell'>"
						+ "		<table class='infotable' >"
						+ "			<tbody>" 
						+ "				<tr>"
						+ "					<td width='20' height='20'>&#160;</td>"
						+ "					<td width='20'>&#160;" + rowInfo + "</td>"
						+ "				</tr>"
						+ "				<tr>"
						+ "					<td height='20'>&#160;" + columnInfo + "</td>"
						+ "					<td>&#160;</td>" 
						+ "				</tr>"
						+ "			</tbody>" 
						+ "		</table>"
						+ "	</h:outputText>";
		return result;
	}
	
	public String getBlockedField() {
		return BLOCKED_FIELD;
	}

	public HashMap<String, String> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, String> resultMap) {
		this.resultMap = resultMap;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String[] getSolved() {
		return solved;
	}

	public void setSolved(String[] solved) {
		this.solved = solved;
	}

	
}