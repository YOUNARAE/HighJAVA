package JavaExam.basic0206;

public class SmartPhoVO {
	private String manufacturer;
	private String model;
	private int memory;
	
	public SmartPhoVO() {
	}
	
	public SmartPhoVO(String manufacturer, String model, int memory) {
		this.manufacturer = manufacturer;
		this.model = model;
		this.memory = memory;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturere(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public int getMemory() {
		return memory;
	}
	
	public void setMemory(int memory) {
		this.memory = memory;
	}

	@Override
	public String toString() {
		return "제조사 : " + manufacturer + ", 모델명 : " + model + ", 용량 : " + memory;
	}
	
	
}
