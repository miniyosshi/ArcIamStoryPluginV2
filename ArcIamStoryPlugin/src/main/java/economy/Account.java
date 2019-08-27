package economy;

//import com.github.miniyosshi.arciamstoryplugin.User;

public class Account {
	private String name;
	private long balance;
	
	Account(String name, long balance){
		this.name = name;
		this.balance = balance;
	}
	
	Account(String name){
		this.name = name;
		//this.balance = 
	}
	
	
	String getName(){
		return name;
	}
	
	long getBalance() {
		return balance;
	}
	
	void withdraw(long x) {
		balance -= x;
	}
	
	void deposit(long x) {
		balance += x;
	}
	

}
