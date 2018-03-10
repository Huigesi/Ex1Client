import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.util.*;

public class Client extends JFrame implements ActionListener {
	final JTextArea txta;
	JTextField txtf;
	JPanel p1;
	JButton bt;
	BufferedReader br;
	DataOutputStream out;
	PrintStream ps;
	Container f = this.getContentPane();

	public Client() {
		f.setLayout(new BorderLayout());
		txta = new JTextArea();
		f.add(txta, BorderLayout.CENTER);
		txtf = new JTextField(20);
		bt = new JButton("send");
		p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		p1.add(txtf);
		p1.add(bt);
		bt.addActionListener(this);
		f.add(p1, BorderLayout.SOUTH);
		setTitle("Client");
		setSize(500, 300);
		setVisible(true);
		run();
		new Thread() {
			{
				start();
			}
			public void run() {
				//
				try {
					txta.append("receive msg+ "+br.readLine()+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
	

	public void actionPerformed(ActionEvent e) {
		//
		if(e.getSource()==bt){
			String msg=txtf.getText();
			ps.println(msg);
			txta.append("have sent msg: "+msg+"\n");
		}
	}

	public void run() {
		Socket st;
		try {
			st = new Socket("10.20.203.18", 8014);
			out=new DataOutputStream(st.getOutputStream());

			br=new BufferedReader(new InputStreamReader(st.getInputStream()));
			ps=new PrintStream(out);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client myclient = new Client();
	}
}
