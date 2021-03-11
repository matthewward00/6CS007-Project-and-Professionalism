package uk.ac.wlv.augmentedmemory;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private String reminder;
    private EditText textReminder;
    private Button enterButton;
    private Button recordButton;

    //private List<String> timeList = new ArrayList<String>(Arrays.asList("am", "pm", "o'clock", "clock"));

    //Array of split words
    //private String[] split;
    private List<String> split = new ArrayList<String>();

    //Lists to be converted to string
    private List<String> timeList = new ArrayList<String>();

    //private Time time;
    private String time;
    private String activity;
    private String date;
    private String location;
    private String people;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        textReminder = (EditText) v.findViewById(R.id.textReminder);

        recordButton = (Button) v.findViewById(R.id.recordButton);
        recordButton.setText("Record Voice");

        enterButton = (Button) v.findViewById(R.id.enterButton);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reminder = textReminder.getText().toString();
                //split = reminder.split("\\s+");
                split = new ArrayList<>(Arrays.asList(reminder.split("\\s+")));
                //timeList = null;
                for (String s : split) {
                    Log.d("Split list", s);
                }
                checkWord();
                for (String s : split) {
                    Log.d("Split list", s);
                }
                for (String s : timeList) {
                    Log.d("Split time", s);
                }
                //keyWord(reminder);
                buttonOut("success");
            }
        });

        return v;
    }

    private void buttonOut (String text) {
        Toast.makeText(getActivity().getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    private String formatTime (String text) {
        String format = "";
        if (text.length() == 4) {
            if (((Integer.parseInt(text.substring(0, 2))) > 23) || ((Integer.parseInt(text.substring(2, 4))) > 59)) {
                return ("99:99");
            } else {
                format = (text.substring(0, 2)) + ":" + (text.substring(2, 4));
                return format;
            }
        } else if (text.length() == 2) {
            if (Integer.parseInt(text) > 23) {
                return ("99:99");
            } else {
                format = (text + ":00");
                return format;
            }
        } else if (text.length() == 1) {
            format = ("0" + text + ":00");
            return format;
        }
        format = "99:99";
        return format;
    }

    private String checkTime (String text) {
        char[] characters = text.toCharArray();
        String timeTemp = "";
        String newTime = "";
        int count = 0;
        for (char c : characters) {
            if ((!Character.isDigit(c)) && (count > 1)) {
                break;
            }
            if (Character.isDigit(c)){
                if (count == 4) {
                    break;
                } else {
                    timeTemp = timeTemp + c;
                    count = count + 1;
                }
            }
        }

        newTime = formatTime(timeTemp);

        if (newTime == ("99:99")) {
            newTime = "12:00";
        }
        return newTime;
    }

    private void checkWord () {
        int timeCount = 0;
        int totalCount = 0;
        List<String> removeItem = new ArrayList<String>();
        String newTime;
        for (String s: split) {
            if (timeCount == 1) {
                newTime = checkTime (s);
                if (newTime != null) {
                    timeList.add(newTime);
                    removeItem.add(Integer.toString(totalCount-1));
                    removeItem.add(Integer.toString(totalCount));
                }
                newTime = "";
                timeCount = 0;
            }
            if (s.equals("at") || s.equals("from") || s.equals("until") || s.equals("till") || s.equals("to")) {
                timeCount = 1;
            }
            totalCount = totalCount + 1;
        }
        /*
        for (String rem: removeItem) {
            split.remove(Integer.parseInt(rem));
        }
        */
        for (int i = removeItem.size() - 1; i >= 0; i--) {
            split.remove(Integer.parseInt(removeItem.get(i)));
        }
    }
}