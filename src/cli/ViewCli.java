package cli;

import java.util.List;
import interfaces.ICompany;
import interfaces.IComputer;

/**
 * View fir Command line interface
 * @author Caltot Stéphan
 *
 * 21 févr. 2017
 */
public class ViewCli {

	@SuppressWarnings("unused")
	private ControllerCli controlerCli;
	
	public static final String FORMAT_COMPUTER = "%3s | %15.15s |%10s | %10s | %10s%n";
	
	public static final String FORMAT_COMPANY  = "%3s | %15.15s%n";

	public final static String FOOTER = "____________________________________________________"; 
	
	
	/**
	 * View constructor setting controller
	 * @param pControlerCli
	 */
	public ViewCli (ControllerCli pControlerCli){
		this.controlerCli = pControlerCli;
	}
	
	
	
	/**
	 * Designed display for CLI home
	 */
	public void welcome() {
		System.out.println("####################################################");
		System.out.println("#                                                  #");
		System.out.println("#                                                  #");
		System.out.println("#          Welcome to the CLI for                  #");
		System.out.println("#                computers                         #");
		System.out.println("#                                                  #");
		System.out.println("#                                                  #");
		System.out.println("####################################################\n\n");
	}
	
	
	
	/**
	 * Designed display for CLI menu
	 */
	public void displayMenu(){
		System.out.println("\n\n");
		System.out.println("----------------------> Menu <----------------------\n" );
		System.out.println("----------------------------------------------------");
		System.out.println("	* Press [1] to display All computers ");
		System.out.println("	* Press [2] to display All companies ");
		System.out.println("	* Press [3] to show computer details ");
		System.out.println("	* Press [4] to create computer ");
		System.out.println("	* Press [5] to update computer ");
		System.out.println("	* Press [6] to delete computer ");
		System.out.println("	* Press [q] to quit command line interface");
		System.out.println("----------------------------------------------------\n\n");

	}
	
	
	
	/**
	 * Method displaying computers formated
	 * @param pComputers
	 */
	public void displayAllComputers(List<IComputer> pComputers){
		System.out.println("\n");
		System.out.printf(FORMAT_COMPUTER, "ID", "NAME" , "INTRODUCED" , "DISCONTINUED" , "COMPANY_ID");
		for (IComputer computer : pComputers){
			System.out.printf(FORMAT_COMPUTER,computer.getId(), computer.getName(),computer.getDateWichIsIntroduced(), computer.getDateWichIsDiscontinued(), ((computer.getManufacturer() == null) ? "No company": computer.getManufacturer().getId()));								
		}
		System.out.println("\n");
	}

	
	
	/**
	 * Method displaying companies formated
	 * @param pCompanies
	 */
	public void displayAllCompanies(List<ICompany> pCompanies){
		System.out.println("\n");
		System.out.printf(FORMAT_COMPANY, "ID", "NAME");
		System.out.println("---------------------");
		for (ICompany company : pCompanies){
			System.out.printf(FORMAT_COMPANY, company.getId(), company.getName() );
		}
		System.out.println("\n");
	}
	
	
	
	/**
	 * Method displaying details for on computer
	 * @param pComputer
	 */
	public void displayComputersDetails(IComputer pComputer){
		System.out.println("\n" + pComputer.display() + "\n");
	}
	
	
	
	/**
	 * Displaying information given in parameter
	 * @param pInfo
	 */
	public void displayInfo(String pInfo){
		System.out.print(pInfo);
	}
	
}
