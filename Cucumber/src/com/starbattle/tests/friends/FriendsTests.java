package com.starbattle.tests.friends;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.starbattle.accounts.player.FriendRelationState;
import com.starbattle.client.testinterface.main.ClientTestInterface;
import com.starbattle.client.testinterface.tester.ClientAutomate;
import com.starbattle.client.views.lobby.friends.AddFriendView;
import com.starbattle.tests.TestEnvironment;
import com.starbattle.tests.TestUsersConfig;

public class FriendsTests {

	private static TestEnvironment testEnvironment;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testEnvironment = new TestEnvironment();

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		testEnvironment.close();
	}

	@Before
	public void setUp() throws Exception {
		// reset db values
		testEnvironment.initDB();
	}

	@After
	public void tearDown() throws Exception {
		testEnvironment.tidyUp();
	}

	@Test
	public void addFriend() throws Exception {

		String myAccountName = TestUsersConfig.getAccountName(0);
		String myUserName = TestUsersConfig.getDisplayName(0);
		String myPassword = TestUsersConfig.getPassword(0);

		String friendUserName = TestUsersConfig.getDisplayName(1);
		String friendAccountName = TestUsersConfig.getAccountName(1);
		String friendPassword = TestUsersConfig.getPassword(1);

		ClientAutomate client = testEnvironment.getClient();
		client.doLogin(myAccountName, myPassword);
		client.clickButton("AddFriendInLobby");
		assertEquals(true, client.isInView(AddFriendView.VIEW_ID));
		client.fillInTextfield("FriendUsername", TestUsersConfig.getDisplayName(1));
		client.clickButton("AddFriend");

		// check new entry in DB (value =1 = Request)
		assertEquals(1, testEnvironment.getTestAccountManager().getFriendState(myAccountName, friendUserName));

		// check my gui
		assertTrue(client.friendRelationStateIs(friendUserName, FriendRelationState.Pending.getId()));
		// kill my client
		client.shutdown();

		// login friend in new client
		client = ClientTestInterface.createNewTestClient();
		client.doLogin(friendAccountName, friendPassword);

		// check friend gui
		assertTrue(client.friendRelationStateIs(myUserName, FriendRelationState.Request.getId()));

	}

}
