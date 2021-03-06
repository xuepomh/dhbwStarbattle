package com.starbattle.client.main.debug;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.player.PlayerValues;
import com.starbattle.client.resource.ClientConfiguration;
import com.starbattle.client.resource.GUIDesign;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.views.lobby.friends.AddFriendView;
import com.starbattle.client.views.login.LoginView;
import com.starbattle.client.views.play.PlayView;
import com.starbattle.client.views.profile.PlayerProfileView;
import com.starbattle.client.views.register.RegisterView;
import com.starbattle.client.views.reset.ResetPasswordView;
import com.starbattle.client.views.settings.SettingsView;
import com.starbattle.client.views.shop.ShopView;
import com.starbattle.client.window.GameWindow;

public class StarBattleClientDebug {

	public static void main(String[] args) {
		
		GUIDesign.load();
		ClientConfiguration.loadConfiguration();
		
		GameWindow window = new GameWindow(null, "Client DEBUG Modus");
		System.out.println("Client DEBUG Modus| Open Client in debug modus");
		NetworkConnection debugConnection=new DebugNetworkConnection(null);
		
		
		//add views to test
		window.addView(new LoginView(debugConnection));
		window.addView(new RegisterView(debugConnection));
		window.addView(new ResetPasswordView(debugConnection));
		window.addView(new LobbyView(debugConnection));
		window.addView(new PlayView(debugConnection));
		window.addView(new AddFriendView(debugConnection));
		window.addView(new ShopView(debugConnection));
		window.addView(new SettingsView(debugConnection));
		window.addView(new PlayerProfileView(debugConnection));
			
		//set starting view
		window.open(LoginView.VIEW_ID);
		
	}
	
}
