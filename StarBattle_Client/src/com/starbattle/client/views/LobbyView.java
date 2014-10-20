package com.starbattle.client.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.window.ContentView;
import com.starbattle.network.connection.objects.NP_Logout;

public class LobbyView extends ContentView{

	public final static int VIEW_ID = 3;
	private Dimension windowSize=new Dimension(1000,600);
	
	
	public LobbyView(final NetworkConnection networkConnection)
	{
		JPanel topPanel=new JPanel(new FlowLayout());
		JButton logout=new JButton("Disconnect");
		JButton profile=new JButton("Profile");
		JButton find=new JButton("Find");
		JButton shop=new JButton("Shop");
		JPanel blocker=new JPanel();
		JPanel centerPanel=new JPanel(new BorderLayout());
		JPanel friendPanel=new JPanel();
		JPanel southWestPanel=new JPanel(new BorderLayout());
		JPanel newsPanel=new JPanel();
		JPanel startPanel=new JPanel();
		
		view.setLayout(new BorderLayout());
		topPanel.add(profile);
		topPanel.add(logout);
		topPanel.setBackground(Color.lightGray);
		view.add(topPanel, BorderLayout.NORTH);
		
		friendPanel.setBackground(Color.green);
		friendPanel.setPreferredSize(new Dimension(300,0));
		centerPanel.add(friendPanel, BorderLayout.EAST);
		
		southWestPanel.add(newsPanel, BorderLayout.CENTER);
		startPanel.setBackground(Color.gray);
		startPanel.setPreferredSize(new Dimension(0,250));
		
		shop.setPreferredSize(new Dimension(140,100));
		shop.setOpaque(true);
		shop.setBackground(new Color(20,140,20));
		startPanel.add(shop);
		blocker.setPreferredSize(new Dimension(150,150));
		blocker.setBackground(startPanel.getBackground());
		startPanel.add(blocker);
		find.setPreferredSize(new Dimension(240,180));
		find.setOpaque(true);
		find.setBackground(new Color(20,20,140));
		startPanel.add(find);
		southWestPanel.add(startPanel, BorderLayout.SOUTH);
		centerPanel.add(southWestPanel, BorderLayout.CENTER);
		view.add(centerPanel, BorderLayout.CENTER);
		
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				networkConnection.getSendConnection().sendTCP(new NP_Logout());
				openView(LoginView.VIEW_ID);
			}
		});
	}
	
	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		resizeWindow(windowSize);
	}

	@Override
	protected void onClosing() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getViewID() {
		// TODO Auto-generated method stub
		return VIEW_ID;
	}

	
}
