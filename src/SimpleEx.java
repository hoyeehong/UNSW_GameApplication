import java.awt.EventQueue;
import javax.swing.JFrame;

public class SimpleEx extends JFrame{
	public SimpleEx(){
		initUI();
	}
	private void initUI(){
		setTitle("Simple Example");
		setSize(800,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args)
	{
		 EventQueue.invokeLater(new Runnable()
		 {
			@Override
			public void run()
			{
				SimpleEx ex = new SimpleEx();
				ex.setVisible(true);
			}
	     });
	}

}