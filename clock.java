import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.*;
import java.time.*;
import java.time.LocalTime;
import javax.swing.Timer.*;
import java.util.TimeZone; 
import java.awt.event.*;
import java.time.temporal.ChronoUnit;
  
public class clock implements ActionListener
{
	static String hr,min,sec, hr1,min1,sec1;
	int set_hr_timer,set_min_timer,set_sec_timer, get_hr_timer,get_min_timer,get_sec_timer;
	int set_hr_watch,set_min_watch,set_sec_watch, get_hr_watch,get_min_watch,get_sec_watch;
	int hr_diff_timer,min_diff_timer,sec_diff_timer;
	int hr_diff_watch,min_diff_watch,sec_diff_watch;
	int dummy_hr_timer,dummy_min_timer,dummy_sec_timer;
	JButton start_button1,stop_button1,reset_button1,start_button,stop_button,reset_button;
	boolean start_timer = false, stop_timer = false, reset_timer = false, stop_use_timer = false, 
			start_watch = false, stop_watch = false, reset_watch = false, stop_use_watch = false;
	boolean limit1 = false, limit2 = false;
	int global_hr, global_min, global_sec;
	int global_hr_watch, global_min_watch, global_sec_watch;
	int count = 0;

	public void GUI()
	{

		JLabel clock_label = new JLabel();
		JLabel stop_clock_label = new JLabel();
		JLabel timer_label = new JLabel();
		JTabbedPane menu = new JTabbedPane();
		
		//Clock
		String[] time_zone = TimeZone.getAvailableIDs();		
		String[] dummy = TimeZone.getAvailableIDs();
		JComboBox<String> list = new JComboBox<>(time_zone);
		JFrame c_frame = new JFrame("Clock");
		clock_label.setBounds(125,125,150,150);
		clock_label.setHorizontalAlignment(JLabel.CENTER);
		clock_label.setVerticalAlignment(JLabel.CENTER);
		clock_label.add(list);
		
		//Stopclock
		JLabel passed_hr = new JLabel();
		JLabel passed_min = new JLabel();
		JLabel passed_sec = new JLabel();
		start_button = new JButton("START");
		stop_button = new JButton("STOP");
		reset_button = new JButton("RESET");
		passed_hr.setBounds(80, 175, 100 , 30);
		passed_min.setBounds(220, 175, 100 , 30);
		passed_sec.setBounds(360, 175, 100 , 30);
		stop_clock_label.add(passed_hr);
		stop_clock_label.add(passed_min);
		stop_clock_label.add(passed_sec);
		start_button.setBounds(75,250,100,50);
		stop_button.setBounds(185,250,100,50);
		reset_button.setBounds(295,250,100,50);
		stop_clock_label.add(start_button);
		stop_clock_label.add(stop_button);
		stop_clock_label.add(reset_button);
		stop_clock_label.setHorizontalAlignment(JLabel.CENTER);
		stop_clock_label.setVerticalAlignment(JLabel.CENTER);
		start_button.addActionListener(this);
		stop_button.addActionListener(this);
		reset_button.addActionListener(this);




		//Timer
		SpinnerModel sec_model = new SpinnerNumberModel(0, 0, 59, 1);
		SpinnerModel min_model = new SpinnerNumberModel(0, 0, 59, 1);
		SpinnerModel hr_model = new SpinnerNumberModel(0, 0, 24, 1);
		JSpinner sec_spin = new JSpinner(sec_model);
		JSpinner min_spin = new JSpinner(min_model);
		JSpinner hr_spin = new JSpinner(hr_model);
		hr_spin.setBounds(50, 100, 70, 50);
		min_spin.setBounds(190, 100, 70, 50);
		sec_spin.setBounds(330, 100, 70, 50);
		timer_label.add(sec_spin);
		timer_label.add(min_spin);
		timer_label.add(hr_spin);
		JLabel hours_label = new JLabel("hour(s)");
		JLabel mins_label = new JLabel("min(s)");
		JLabel secs_label = new JLabel("sec(s)");
		hours_label.setBounds(130, 100, 50, 50);
		mins_label.setBounds(270, 100, 50, 50);
		secs_label.setBounds(410, 100, 50, 50);
		timer_label.add(hours_label);
		timer_label.add(mins_label);
		timer_label.add(secs_label);
		start_button1 = new JButton("START");
		stop_button1 = new JButton("STOP");
		reset_button1 = new JButton("RESET");
		start_button1.addActionListener(this);
		stop_button1.addActionListener(this);
		reset_button1.addActionListener(this);
		start_button1.setBounds(75,250,100,50);
		stop_button1.setBounds(185,250,100,50);
		reset_button1.setBounds(295,250,100,50);
		timer_label.add(start_button1);
		timer_label.add(stop_button1);
		timer_label.add(reset_button1);
		
		
		JLabel rem_hr = new JLabel();
		JLabel rem_min = new JLabel();
		JLabel rem_sec = new JLabel();
		rem_hr.setBounds(80, 175, 100 , 30);
		rem_min.setBounds(220, 175, 100 , 30);
		rem_sec.setBounds(360, 175, 100 , 30);
		timer_label.add(rem_hr);
		timer_label.add(rem_min);
		timer_label.add(rem_sec);
		JLabel hours1_label = new JLabel("hour(s)");
		JLabel mins1_label = new JLabel("min(s)");
		JLabel secs1_label = new JLabel("sec(s)");
		hours1_label.setBounds(130, 165, 50, 50);
		mins1_label.setBounds(270, 165, 50, 50);
		secs1_label.setBounds(410, 165, 50, 50);
		timer_label.add(hours1_label);
		timer_label.add(mins1_label);
		timer_label.add(secs1_label);
		
		String time;
		menu.setBounds(0,0,500,500);
		c_frame.setSize(500,500);
		list.setBounds(20,20,150,50);
		menu.add("World Time",clock_label);
		menu.add("Stop Clock",stop_clock_label);
		menu.add("Timer",timer_label);
		c_frame.add(menu);
		c_frame.setVisible(true);
		String zone = "Asia/Kolkata";	
			

		for(;;)
		{	
			//CLOCK
			time = LocalTime.now(Clock.system(ZoneId.of(zone))).toString();
			zone = list.getSelectedItem().toString(); 
			clock_label.setText(time.substring(0,8));
			clock_label.setFont(new Font("Arial", Font.BOLD, 40));
			c_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


			//TIMER
			hr = hr_spin.getValue()+"";
			min = min_spin.getValue()+"";
			sec = sec_spin.getValue()+"";
			dummy_hr_timer = (Integer)hr_spin.getValue();
			dummy_min_timer = (Integer)min_spin.getValue();
			dummy_sec_timer = (Integer)sec_spin.getValue();

			//STOP WATCH
		
			
			if(start_timer == true && stop_use_timer == false)
			{
				hr_diff_timer = global_hr;
				min_diff_timer = global_min;
				sec_diff_timer = global_sec;
				get_hr_timer = LocalTime.now().getHour();
				get_min_timer = LocalTime.now().getMinute();
				get_sec_timer = LocalTime.now().getSecond();
				int seconds_timer = (int)ChronoUnit.SECONDS.between(LocalTime.of(get_hr_timer,get_min_timer,get_sec_timer), LocalTime.of(set_hr_timer,set_min_timer,set_sec_timer)) % 60;
        		sec_diff_timer = sec_diff_timer + seconds_timer;
        		if(min_diff_timer > 0 && sec_diff_timer == 0)
        		{
        			sec_diff_timer = 60;
        			global_sec = 60;
        			if(min_diff_timer > 0)
        			{
        				min_diff_timer--;
        				global_min--;
        			}
        		}
        		if(hr_diff_timer > 0 && min_diff_timer == 0)
        		{
        			min_diff_timer = 60;
        			global_min = 60;
        			if(hr_diff_timer > 0)
        			{
        				hr_diff_timer--;
        				global_hr--;
        			}
        		}
        		
        		if(hr_diff_timer < 0 && min_diff_timer < 0 && sec_diff_timer < 0)
        		{
        			hr_diff_timer = global_hr = 0;
					min_diff_timer = global_min = 0;
					sec_diff_timer = global_sec = 0;
        		}
        		hr = String.valueOf(hr_diff_timer);
        		min = String.valueOf(min_diff_timer);
        		sec = String.valueOf(sec_diff_timer);
			}
			else if(stop_timer == true)
			{
				hr = String.valueOf(hr_diff_timer);
        		min = String.valueOf(min_diff_timer);
        		sec = String.valueOf(sec_diff_timer);
        		global_hr = hr_diff_timer;
        		global_min = min_diff_timer;
        		global_sec = sec_diff_timer;	
			}
			else if(stop_use_timer == true && start_timer == true)
			{
				hr_diff_timer = global_hr;
				min_diff_timer = global_min;
				sec_diff_timer = global_sec;
				get_hr_timer = LocalTime.now().getHour();
				get_min_timer = LocalTime.now().getMinute();
				get_sec_timer = LocalTime.now().getSecond();
				int seconds_timer = (int)ChronoUnit.SECONDS.between(LocalTime.of(get_hr_timer,get_min_timer,get_sec_timer), LocalTime.of(set_hr_timer,set_min_timer,set_sec_timer)) % 60;
        		sec_diff_timer = sec_diff_timer + seconds_timer;
        		
        		if(min_diff_timer > 0 && sec_diff_timer == 0)
        		{
        			sec_diff_timer = 59;
        			global_sec = 59;
        			if(min_diff_timer > 0)
        			{
        				min_diff_timer--;
        				global_min--;
        			}
        		}
        		if(hr_diff_timer > 0 && min_diff_timer == 0)
        		{
        			min_diff_timer = 59;
        			global_min = 59; 
        			if(hr_diff_timer > 0)
        			{
        				hr_diff_timer--;
        				global_hr--;
        			}
        		}
        		
        		if(hr_diff_timer < 0 && min_diff_timer < 0 && sec_diff_timer < 0)
        		{
        			hr_diff_timer = global_hr = 0;
					min_diff_timer = global_min = 0;
					sec_diff_timer = global_sec = 0;
        		}
        		hr = String.valueOf(hr_diff_timer);
        		min = String.valueOf(min_diff_timer);
        		sec = String.valueOf(sec_diff_timer);
			}
			else if(reset_timer == true)
			{
				hr = hr_spin.getValue()+"";
				min = min_spin.getValue()+"";
				sec = sec_spin.getValue()+"";
				dummy_hr_timer = (Integer)hr_spin.getValue();
				dummy_min_timer = (Integer)min_spin.getValue();
				dummy_sec_timer = (Integer)sec_spin.getValue();
			}
			rem_hr.setText(hr);
			rem_min.setText(min);
			rem_sec.setText(sec);
			rem_hr.setFont(new Font("Arial", Font.BOLD, 20));
			rem_min.setFont(new Font("Arial", Font.BOLD, 20));
			rem_sec.setFont(new Font("Arial", Font.BOLD, 20));

			//STOPWATCH
			if(start_watch == true)
			{
				
        		System.out.println(global_sec_watch);
				get_hr_watch = LocalTime.now().getHour();
				get_min_watch = LocalTime.now().getMinute();
				get_sec_watch = LocalTime.now().getSecond();
        		int seconds_watch = (int)ChronoUnit.SECONDS.between(LocalTime.of(set_hr_watch,set_min_watch,set_sec_watch), LocalTime.of(get_hr_watch,get_min_watch,get_sec_watch)) % 61;
        		sec_diff_watch = seconds_watch;


        		if(sec_diff_watch > 59 && limit1 == false)
        		{
        			min_diff_watch++;
        			sec_diff_watch = 0;
        			limit1 = true;
        		}
        		else if(sec_diff_watch < 59)
        		{
        			limit1 = false;
        		}
        		if(min_diff_watch > 59 && limit2 == false)
        		{
        			hr_diff_watch++;
        			min_diff_watch = 0;
        			limit2 = true;
        		}
        		else if(min_diff_watch < 59)
        		{
        			limit2 = false;
        		}
        		hr1 = String.valueOf(hr_diff_watch);
        		min1 = String.valueOf(min_diff_watch);
        		sec1= String.valueOf(sec_diff_watch);
        		global_hr_watch = hr_diff_watch;
        		global_min_watch = min_diff_watch;
        		global_sec_watch = sec_diff_watch;
        	}
        	if(stop_watch == true)
        	{
        		hr_diff_watch = global_hr_watch;
        		min_diff_watch = global_min_watch;
        		sec_diff_watch = global_sec_watch;
        		hr1 = String.valueOf(hr_diff_watch);
        		min1 = String.valueOf(min_diff_watch);
        		sec1 = String.valueOf(sec_diff_watch);
        	}	
        	if(start_watch == true && stop_use_watch == true)
        	{
        		hr_diff_watch = global_hr_watch;
        		min_diff_watch = global_min_watch;
        		sec_diff_watch = global_sec_watch;
        		get_hr_watch = LocalTime.now().getHour();
				get_min_watch = LocalTime.now().getMinute();
				get_sec_watch = LocalTime.now().getSecond();
				int seconds_watch = (int)ChronoUnit.SECONDS.between(LocalTime.of(set_hr_watch,set_min_timer,set_sec_watch), LocalTime.of(get_hr_watch,get_min_watch,get_sec_watch)) % 61;
				System.out.println(sec1);
				if(count < seconds_watch)
				{
					sec_diff_watch = sec_diff_watch + seconds_watch;
				}
				count = seconds_watch;
        		if(sec_diff_watch > 59 && limit1 == false)
        		{
        			min_diff_watch++;
        			sec_diff_watch = 0;
        			limit1 = true;
        		}
        		else if(sec_diff_watch < 59)
        		{
        			limit1 = false;
        		}
        		if(min_diff_watch > 59 && limit2 == false)
        		{
        			hr_diff_watch++;
        			min_diff_watch = 0;
        			limit2 = true;
        		}
        		else if(min_diff_watch < 59)
        		{
        			limit2 = false;
        		}
        		hr1 = String.valueOf(hr_diff_watch);
        		min1 = String.valueOf(min_diff_watch);
        		sec1 = String.valueOf(sec_diff_watch);
        	}

        	if(reset_watch == true)
        	{
        			hr_diff_watch = 0;
        			min_diff_watch = 0;
        			sec_diff_watch = 0;
        	}
        	passed_hr.setText(hr1);
			passed_min.setText(min1);
			passed_sec.setText(sec1);
			passed_hr.setFont(new Font("Arial", Font.BOLD, 20));
			passed_min.setFont(new Font("Arial", Font.BOLD, 20));
			passed_sec.setFont(new Font("Arial", Font.BOLD, 20));
		}
	}
	public static void main(String[] args)

	{
		clock c = new clock();
		c.GUI();
	}
	public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == start_button1)
			{
				start_timer = true;
				stop_timer = false;
				reset_timer = false;
				set_hr_timer = LocalTime.now().getHour();
				set_min_timer = LocalTime.now().getMinute();
				set_sec_timer = LocalTime.now().getSecond();
				if(stop_use_timer == false)
				{
					global_hr = dummy_hr_timer;
					global_min = dummy_min_timer;
					global_sec = dummy_sec_timer;
				}
			}
			if(e.getSource() == stop_button1)
			{
				stop_timer = true;
				stop_use_timer = true;
				start_timer = false;
				reset_timer = false;
			}
			if(e.getSource() == reset_button1)
			{
				stop_timer = false;
				stop_use_timer = false;
				start_timer = false;
				reset_timer = true;
				global_hr = dummy_hr_timer;
				global_min = dummy_min_timer;
				global_sec = dummy_sec_timer;
			}
			if(e.getSource() == start_button)
			{
				start_watch = true;
				stop_watch = false;
				reset_watch = false;
				set_hr_watch = LocalTime.now().getHour();
				set_min_watch = LocalTime.now().getMinute();
				set_sec_watch = LocalTime.now().getSecond();
				if(stop_use_watch == false)
				{
					hr_diff_watch = 0;
        			min_diff_watch = 0;
        			sec_diff_watch = 0;
				
				}
				if(stop_use_watch == true)
				{
						global_hr_watch = hr_diff_watch;
        		global_min_watch = min_diff_watch;
        		global_sec_watch = sec_diff_watch;
        		System.out.println("continue " + global_sec_watch);
				}
			}
			if(e.getSource() == stop_button)
			{
				stop_watch = true;
				stop_use_watch = true;
				start_watch = false;
				reset_watch = false;
				global_hr_watch = hr_diff_watch;
        		global_min_watch = min_diff_watch;
        		global_sec_watch = sec_diff_watch;
        		System.out.println("stop " + global_sec_watch);
			}
			if(e.getSource() == reset_button)
			{
				stop_watch = false;
				stop_use_watch = false;
				start_watch = false;
				reset_watch = true;
				hr_diff_watch = 0;
        		min_diff_watch = 0;
        		sec_diff_watch = 0;
			}
		}
}