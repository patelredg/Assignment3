package library.Testcases;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import library.daos.BookHelper;
import library.daos.BookMapDAO;
import library.daos.LoanHelper;
import library.daos.LoanMapDAO;
import library.daos.MemberHelper;
import library.daos.MemberMapDAO;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.entities.IBook;
import library.interfaces.entities.IMember;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestControlDaos {

	@Spy
    private IBookDAO bookDAO = new BookMapDAO(new BookHelper());
	@Spy
	private ILoanDAO loanDAO=new LoanMapDAO(new LoanHelper());
	@Spy
    private IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
	
	
	@Before
	public void setUp() throws Exception {
        setUpTestData();
    }
	@Test
    public void testBookDAOS() {	    	
		 	bookDAO.addBook("AutherDVZ", "JAVA", "callNO786");
		 	//Verify Auther DVZ Called
	        verify(bookDAO,times(1)).addBook("AutherDVZ", "JAVA", "callNO786");
	        //Verify Auther1 Called
	        verify(bookDAO,times(1)).addBook("author1", "title1", "callNo1");
	        System.out.println("BookDAO Add Book Operation Successfully Called");	        
	        bookDAO.getBookByID(1);
	        verify(bookDAO,times(1)).getBookByID(1);
	        System.out.println("BookDAO Get BookById Successfully Called");
	        bookDAO.findBooksByAuthor("AutherDVZ");
	        verify(bookDAO,times(1)).findBooksByAuthor("AutherDVZ");
	        System.out.println("BookDAO FindBooksByAuther Successfully Called");
    }	 
	@Test
	public void testMemberMapDAOS(){	
		verify(memberDAO,times(1)).addMember("fName0", "lName0", "0001", "email0");
		System.out.println("MemeberDAO Add Memeber Called Success");
		memberDAO.findMembersByNames("fName0", "lName0");
		verify(memberDAO,times(1)).findMembersByNames("fName0", "lName0");
		System.out.println("MemeberDAO Find Memebrby Names Called Success");
	}
	@Test
	public void testLoanMapDAOS() {
		loanDAO.createLoan(memberDAO.getMemberByID(1),bookDAO.getBookByID(1));		
		verify(loanDAO,times(1)).createLoan(memberDAO.getMemberByID(1),bookDAO.getBookByID(1));
		System.out.println("Loan Created Successfully");
		
	}
	public void setUpTestData(){
		IBook[] book = new IBook[15];
		book[0]  = bookDAO.addBook("author1", "title1", "callNo1");
		IMember[] member = new IMember[6];		
		member[0] = memberDAO.addMember("fName0", "lName0", "0001", "email0");
        loanDAO.commitLoan(loanDAO.createLoan(member[0], book[0]));
		
		
	}
}
