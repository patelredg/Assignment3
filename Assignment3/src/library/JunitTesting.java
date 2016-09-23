package library;

import static org.mockito.Mockito.verify;
import library.daos.BookHelper;
import library.daos.BookMapDAO;
import library.daos.LoanHelper;
import library.daos.LoanMapDAO;
import library.daos.MemberHelper;
import library.daos.MemberMapDAO;
import library.hardware.CardReader;
import library.hardware.Display;
import library.hardware.Printer;
import library.hardware.Scanner;
import library.interfaces.EBorrowState;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.IMemberDAO;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

//@RunWith(MockitoJUnitRunner.class)
public class JunitTesting {

	private IBookDAO bookDAO = new BookMapDAO(new BookHelper());
	@Spy
	private BorrowUC_CTL sut;
	private IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
	private ILoanDAO loanDAO = new LoanMapDAO(new LoanHelper());
	private CardReader reader;
	private Scanner scanner;
	private Printer printer;
	private Display display;
	private BorrowUC_UI ui;
	
	@Spy
    private IBookDAO spybookDAO = Mockito.spy(new BookMapDAO(new BookHelper()));
    @Spy
    private IMemberDAO spymemberDAO = Mockito.spy(new MemberMapDAO(new MemberHelper()));    
    @Spy
    private ILoanDAO spyloanDAO = Mockito.spy(new LoanMapDAO(new LoanHelper()));
    @Spy
    private BorrowUC_UI spyui;
/*    @InjectMocks
    private BorrowUC_CTL spysut;*/
    
    @Before
    public void setUp() throws Exception {
        setUpTestData();
    }
    @Test
    public void testInit() {
    	System.out.println("reader Object initialize"+reader.getSize());
       // verify(spyreader).setEnabled(true);
        //verify().setEnabled(false);
        verify(spyui,Mockito.times(1)).setState(EBorrowState.INITIALIZED);
    }
    
	private void setUpTestData() {
		System.out.println("Setup test data called");
		bookDAO = new BookMapDAO(new BookHelper());
		loanDAO = new LoanMapDAO(new LoanHelper());
		memberDAO = new MemberMapDAO(new MemberHelper());
		reader=new CardReader();
		scanner=new Scanner();
		printer=new Printer(); 
		display=new Display();
		
		spybookDAO=bookDAO;
		spyloanDAO=loanDAO;
		spymemberDAO=memberDAO;
		//spyreader=reader;
    	sut=Mockito.spy(new BorrowUC_CTL(reader, scanner, printer, display, 
				 bookDAO, loanDAO, memberDAO));
    	spyui =  Mockito.spy(new BorrowUC_UI(sut));
    	spyui.setState(EBorrowState.INITIALIZED);
    	//new BorrowUC_UI(sut);

		
		
	}
	
}
