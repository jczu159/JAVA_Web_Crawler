package com.fubon.esb.bo;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.fubon.esb.core.Login;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JPasswordField;

public class gatherinformation {

	private JFrame frame;
	private JTextField readPathText;

	private static String path = "";
	private static String sec = "";
	private static String strategyName = "";
	private boolean airshipCheckBoxValue = false; // 幸運飛艇是否有打開值
	private boolean speedRacingCheckBoxValue = false; // 極速賽車是否有打開值
	private boolean stegosaurusCheckBoxValue = false; // 劍龍策略是否有開啟
	private String luckBitMoneyList[] = null; // 下單陣列 這要回傳後台的
	private String racngCarBitMoneyList[] = null; // 下單陣列 這要回傳後台的
	private String stegosaurusBitMoneyList[] = null; // 下單陣列 這要回傳後台的
	private String stegosaurusDouble; // 劍龍策略 掛之後澳加碼幾倍

	private JTextField delaySecondsText;
	private JTextField strategyNameText;
	private JTextField luckyAirshipOrderAmount; // 幸運飛艇下單金額
	private JTextField carOrderAmount; // 極速賽車下單金額
	private JTextField checkAccount; // 檢核帳號
	private String version = "V1.3.14";
	private JTextField textField;
	private JTextField stegosaurusBitMony;
	private JTextField stegosaurusDoubletext;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gatherinformation window = new gatherinformation();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gatherinformation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("金石彩票全自動化交易系統");
		readPathText = new JTextField(); // 讀取路徑
		delaySecondsText = new JTextField(); // 延遲秒數
		strategyNameText = new JTextField(); // 策略名稱

		// new 出來區域
		JCheckBox luckyAirshipCheckBox = new JCheckBox("酉道飛艇策略");
		JCheckBox speedRacingCheckBox = new JCheckBox("運行極速賽車");
		JCheckBox stegosaurusCheckBox = new JCheckBox("劍龍策略");

		frame.setBounds(100, 100, 730, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.EAST, strategyNameText, -356, SpringLayout.EAST,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, delaySecondsText, 497, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, strategyNameText, 91, SpringLayout.NORTH,
				frame.getContentPane());
		frame.getContentPane().setLayout(springLayout);

		JButton srartbutton = new JButton("開始運行");
		springLayout.putConstraint(SpringLayout.WEST, srartbutton, 439, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(srartbutton);
		srartbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("程式開始運行．．．");
				if (strategyName.isEmpty() || path.isEmpty() || sec.isEmpty() || luckBitMoneyList == null
						|| racngCarBitMoneyList == null) {
					JOptionPane.showMessageDialog(null, "您未設置運行參數，或尚未按保存設定!!", "錯誤", JOptionPane.ERROR_MESSAGE);
				} else {
					HashMap<String, int[]> betList = new HashMap<>();
					// 兩套策略金額要回傳
					betList.put("luckAirshpBitMoney", StringArrToIntArr(luckBitMoneyList));
					betList.put("carRacingBitMoney", StringArrToIntArr(racngCarBitMoneyList));
					betList.put("stegosaurusBitMoney", StringArrToIntArr(stegosaurusBitMoneyList));

					System.out.println("幸運飛艇下注金額:" + luckBitMoneyList);

					HashMap<String, Boolean> strategySwitch = new HashMap<>();
					strategySwitch.put("airshipCheckBoxValue", airshipCheckBoxValue);
					strategySwitch.put("speedRacingCheckBoxValue", speedRacingCheckBoxValue);
					strategySwitch.put("stegosaurusCheckBoxValue", stegosaurusCheckBoxValue);

					System.out.println("幸運飛艇是否打開值:" + airshipCheckBoxValue);
					System.out.println("極速賽車是否有打開值:" + speedRacingCheckBoxValue);
					System.out.println("劍龍策略是否有打開值:" + stegosaurusCheckBoxValue);

					HashMap<String, String> accPas = new HashMap<>();
					accPas.put("account", "jackson15988");
					accPas.put("password", "536225ab");

					HashMap<String, Object> otherParameters = new HashMap<>();
					otherParameters.put("rxecutiveRacing", "1"); //
					otherParameters.put("stegosaurusDouble", stegosaurusDouble); //建隆策略掛之後加碼幾倍
		

					try {
						Login.loginWEB(path, strategyName, betList, strategySwitch, accPas, otherParameters);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				try {

				} catch (Exception e1) {

				}
			}
		});

		JButton btnNewsaveSettings = new JButton("停止運行");
		JLabel AirshipOrderText = new JLabel("極速賽車下單順序");
		springLayout.putConstraint(SpringLayout.NORTH, srartbutton, 0, SpringLayout.NORTH, btnNewsaveSettings);
		springLayout.putConstraint(SpringLayout.EAST, srartbutton, -6, SpringLayout.WEST, btnNewsaveSettings);
		springLayout.putConstraint(SpringLayout.WEST, btnNewsaveSettings, 532, SpringLayout.WEST,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewsaveSettings, -10, SpringLayout.SOUTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewsaveSettings, -34, SpringLayout.EAST,
				frame.getContentPane());
		frame.getContentPane().add(btnNewsaveSettings);
		btnNewsaveSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("中止");
				try {

					System.exit(1);
				} catch (Exception e1) {

				}
			}
		});

		JLabel label = new JLabel("讀取檔案路徑");
		springLayout.putConstraint(SpringLayout.WEST, readPathText, 6, SpringLayout.EAST, label);
		springLayout.putConstraint(SpringLayout.NORTH, delaySecondsText, -3, SpringLayout.NORTH, label);
		springLayout.putConstraint(SpringLayout.NORTH, readPathText, -3, SpringLayout.NORTH, label);
		frame.getContentPane().add(label);
		frame.getContentPane().add(readPathText);
		readPathText.setColumns(10);

		JButton button = new JButton("預設");
		springLayout.putConstraint(SpringLayout.NORTH, button, 0, SpringLayout.NORTH, srartbutton);
		frame.getContentPane().add(button);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					readPathText.setText("D:/LuckeyAirship/20200225.txt");
					delaySecondsText.setText("40");
					strategyNameText.setText("彩票A計畫");
					luckyAirshipOrderAmount.setText("10,20,40,80,160,320,650,1310");
					carOrderAmount.setText("10,20,40,80,160,320,650,1310");
					stegosaurusBitMony.setText("10,20,40");
				} catch (Exception e1) {

				}
			}
		});

		JButton saveSettings = new JButton("保存設定");
		springLayout.putConstraint(SpringLayout.EAST, button, -6, SpringLayout.WEST, saveSettings);
		springLayout.putConstraint(SpringLayout.NORTH, saveSettings, 0, SpringLayout.NORTH, srartbutton);
		springLayout.putConstraint(SpringLayout.EAST, saveSettings, -6, SpringLayout.WEST, srartbutton);
		frame.getContentPane().add(saveSettings);
		frame.getContentPane().add(delaySecondsText);
		delaySecondsText.setColumns(10);

		JLabel label_1 = new JLabel("延遲秒數");
		springLayout.putConstraint(SpringLayout.EAST, readPathText, -118, SpringLayout.WEST, label_1);
		springLayout.putConstraint(SpringLayout.NORTH, label_1, 0, SpringLayout.NORTH, label);
		springLayout.putConstraint(SpringLayout.EAST, label_1, -6, SpringLayout.WEST, delaySecondsText);
		frame.getContentPane().add(label_1);
		frame.getContentPane().add(strategyNameText);
		strategyNameText.setColumns(10);

		JLabel label_2 = new JLabel("策略名稱");
		springLayout.putConstraint(SpringLayout.NORTH, label_2, 94, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, label_2, -6, SpringLayout.WEST, strategyNameText);
		springLayout.putConstraint(SpringLayout.SOUTH, label, -20, SpringLayout.NORTH, label_2);
		springLayout.putConstraint(SpringLayout.EAST, label, 0, SpringLayout.EAST, label_2);
		frame.getContentPane().add(label_2);

		JLabel label_3 = new JLabel("幸運飛艇下注排序");
		frame.getContentPane().add(label_3);

		luckyAirshipOrderAmount = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, luckyAirshipOrderAmount, 126, SpringLayout.WEST,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, luckyAirshipOrderAmount, -356, SpringLayout.EAST,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, label_3, 3, SpringLayout.NORTH, luckyAirshipOrderAmount);
		springLayout.putConstraint(SpringLayout.EAST, label_3, -6, SpringLayout.WEST, luckyAirshipOrderAmount);
		springLayout.putConstraint(SpringLayout.WEST, strategyNameText, 0, SpringLayout.WEST, luckyAirshipOrderAmount);
		frame.getContentPane().add(luckyAirshipOrderAmount);
		luckyAirshipOrderAmount.setColumns(10);

		springLayout.putConstraint(SpringLayout.EAST, AirshipOrderText, 0, SpringLayout.EAST, label);
		frame.getContentPane().add(AirshipOrderText);

		carOrderAmount = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, carOrderAmount, 170, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, AirshipOrderText, 3, SpringLayout.NORTH, carOrderAmount);
		springLayout.putConstraint(SpringLayout.SOUTH, luckyAirshipOrderAmount, -18, SpringLayout.NORTH,
				carOrderAmount);
		springLayout.putConstraint(SpringLayout.WEST, carOrderAmount, 126, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, carOrderAmount, 0, SpringLayout.EAST, luckyAirshipOrderAmount);
		frame.getContentPane().add(carOrderAmount);
		carOrderAmount.setColumns(10);

		springLayout.putConstraint(SpringLayout.NORTH, luckyAirshipCheckBox, 28, SpringLayout.SOUTH, carOrderAmount);
		springLayout.putConstraint(SpringLayout.WEST, luckyAirshipCheckBox, 0, SpringLayout.WEST, readPathText);
		frame.getContentPane().add(luckyAirshipCheckBox);
		luckyAirshipCheckBox.isSelected();

		checkAccount = new JTextField();
		frame.getContentPane().add(checkAccount);
		checkAccount.setColumns(10);

		JLabel label_5 = new JLabel("運行帳號");
		springLayout.putConstraint(SpringLayout.WEST, checkAccount, 6, SpringLayout.EAST, label_5);
		springLayout.putConstraint(SpringLayout.EAST, label_5, 0, SpringLayout.EAST, label);
		frame.getContentPane().add(label_5);

		springLayout.putConstraint(SpringLayout.NORTH, speedRacingCheckBox, 0, SpringLayout.NORTH,
				luckyAirshipCheckBox);
		springLayout.putConstraint(SpringLayout.WEST, speedRacingCheckBox, 6, SpringLayout.EAST, luckyAirshipCheckBox);
		frame.getContentPane().add(speedRacingCheckBox);

		JLabel lblNewLabel = new JLabel("運行策略方案");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 35, SpringLayout.SOUTH, AirshipOrderText);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, 0, SpringLayout.EAST, label);
		frame.getContentPane().add(lblNewLabel);

		JLabel label_6 = new JLabel("");
		springLayout.putConstraint(SpringLayout.WEST, label_6, 314, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, label_6, 0, SpringLayout.SOUTH, srartbutton);
		frame.getContentPane().add(label_6);

		JLabel lblVersion = new JLabel("版本號碼 : " + version);
		springLayout.putConstraint(SpringLayout.EAST, lblVersion, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(lblVersion);

		JCheckBox checkBox = new JCheckBox("官網大小單雙");
		springLayout.putConstraint(SpringLayout.WEST, checkBox, 6, SpringLayout.EAST, speedRacingCheckBox);
		springLayout.putConstraint(SpringLayout.SOUTH, checkBox, 0, SpringLayout.SOUTH, luckyAirshipCheckBox);
		frame.getContentPane().add(checkBox);

		springLayout.putConstraint(SpringLayout.NORTH, stegosaurusCheckBox, 6, SpringLayout.SOUTH,
				luckyAirshipCheckBox);
		springLayout.putConstraint(SpringLayout.WEST, stegosaurusCheckBox, 0, SpringLayout.WEST, readPathText);
		frame.getContentPane().add(stegosaurusCheckBox);

		JLabel label_4 = new JLabel("官網大小單雙");
		springLayout.putConstraint(SpringLayout.WEST, label_4, 16, SpringLayout.EAST, luckyAirshipOrderAmount);
		springLayout.putConstraint(SpringLayout.SOUTH, label_4, 0, SpringLayout.SOUTH, label_3);
		frame.getContentPane().add(label_4);

		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 54, SpringLayout.SOUTH, delaySecondsText);
		springLayout.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, label_4);
		springLayout.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.EAST, btnNewsaveSettings);
		textField.setColumns(10);
		frame.getContentPane().add(textField);

		JLabel lblNewLabel_1 = new JLabel("劍龍策略籌碼");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 0, SpringLayout.NORTH, AirshipOrderText);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, label_4);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel label_7 = new JLabel("剩餘點數:1005");
		springLayout.putConstraint(SpringLayout.NORTH, lblVersion, 0, SpringLayout.NORTH, label_7);
		springLayout.putConstraint(SpringLayout.NORTH, label_7, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, label_7, 0, SpringLayout.WEST, label_3);
		frame.getContentPane().add(label_7);

		stegosaurusBitMony = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, stegosaurusBitMony, 18, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, stegosaurusBitMony, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.EAST, stegosaurusBitMony, -112, SpringLayout.EAST, btnNewsaveSettings);
		stegosaurusBitMony.setColumns(10);
		frame.getContentPane().add(stegosaurusBitMony);

		JLabel label_8 = new JLabel("掛加碼幾倍");
		springLayout.putConstraint(SpringLayout.NORTH, label_8, 0, SpringLayout.NORTH, AirshipOrderText);
		springLayout.putConstraint(SpringLayout.WEST, label_8, 6, SpringLayout.EAST, stegosaurusBitMony);
		frame.getContentPane().add(label_8);

		stegosaurusDoubletext = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, stegosaurusDoubletext, -3, SpringLayout.NORTH, AirshipOrderText);
		springLayout.putConstraint(SpringLayout.WEST, stegosaurusDoubletext, -71, SpringLayout.EAST,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, stegosaurusDoubletext, 0, SpringLayout.EAST, btnNewsaveSettings);
		frame.getContentPane().add(stegosaurusDoubletext);
		stegosaurusDoubletext.setColumns(10);

		JLabel label_9 = new JLabel("劍龍策略執行名次");
		springLayout.putConstraint(SpringLayout.NORTH, label_9, 48, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, label_9, 0, SpringLayout.EAST, label);
		frame.getContentPane().add(label_9);

		JLabel label_11 = new JLabel("運行密碼");
		springLayout.putConstraint(SpringLayout.SOUTH, label_11, -39, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, label_5, -7, SpringLayout.NORTH, label_11);
		springLayout.putConstraint(SpringLayout.WEST, label_11, 0, SpringLayout.WEST, label_2);
		frame.getContentPane().add(label_11);

		passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, 434, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, checkAccount, -6, SpringLayout.NORTH, passwordField);
		springLayout.putConstraint(SpringLayout.EAST, checkAccount, 0, SpringLayout.EAST, passwordField);
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, readPathText);
		springLayout.putConstraint(SpringLayout.EAST, passwordField, -472, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(passwordField);

		JButton button_1 = new JButton("第一名");
		springLayout.putConstraint(SpringLayout.NORTH, button_1, 6, SpringLayout.SOUTH, stegosaurusCheckBox);
		springLayout.putConstraint(SpringLayout.WEST, button_1, 0, SpringLayout.WEST, readPathText);
		frame.getContentPane().add(button_1);

		JButton button_2 = new JButton("第二名");
		springLayout.putConstraint(SpringLayout.NORTH, button_2, 0, SpringLayout.NORTH, button_1);
		springLayout.putConstraint(SpringLayout.EAST, button_2, 0, SpringLayout.EAST, label_6);
		frame.getContentPane().add(button_2);

		JButton button_3 = new JButton("第三名");
		springLayout.putConstraint(SpringLayout.NORTH, button_3, 0, SpringLayout.NORTH, button_1);
		springLayout.putConstraint(SpringLayout.EAST, button_3, 0, SpringLayout.EAST, saveSettings);
		frame.getContentPane().add(button_3);

		JButton button_4 = new JButton("第四名");
		springLayout.putConstraint(SpringLayout.NORTH, button_4, 0, SpringLayout.NORTH, button_1);
		springLayout.putConstraint(SpringLayout.WEST, button_4, 55, SpringLayout.EAST, button_3);
		frame.getContentPane().add(button_4);

		JButton button_5 = new JButton("第五名");
		springLayout.putConstraint(SpringLayout.NORTH, button_5, 0, SpringLayout.NORTH, button_1);
		springLayout.putConstraint(SpringLayout.WEST, button_5, 32, SpringLayout.EAST, button_4);
		frame.getContentPane().add(button_5);

		saveSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					path = readPathText.getText();
					sec = delaySecondsText.getText();
					strategyName = strategyNameText.getText();
					airshipCheckBoxValue = luckyAirshipCheckBox.isSelected(); // 設定airship是否有開啟
					String luckBitMoney = luckyAirshipOrderAmount.getText();
					luckBitMoneyList = luckBitMoney.split(",");
					String carRacingStr = carOrderAmount.getText();
					racngCarBitMoneyList = carRacingStr.split(",");
					String stegosaurusStr = stegosaurusBitMony.getText();
					stegosaurusBitMoneyList = stegosaurusStr.split(",");
					stegosaurusDouble = stegosaurusDoubletext.getText();
					speedRacingCheckBoxValue = speedRacingCheckBox.isSelected();
					stegosaurusCheckBoxValue = stegosaurusCheckBox.isSelected();
				} catch (Exception e1) {

				}
			}
		});
	}

	public static int[] StringArrToIntArr(String[] s) {
		int[] result = new int[s.length];
		for (int i = 0; i < s.length; i++) {
			result[i] = Integer.parseInt(s[i]);
		}
		return result;
	}
}