package com.example.testme;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class questionAdapter extends ArrayAdapter<eachquestion> {
    RadioGroup radioUser;



    public questionAdapter(Context context, ArrayList<eachquestion> questions) {
        super(context, 0, questions);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.question_list, parent, false);
        }



        eachquestion currentWord = getItem(position);





        TextView quesnumTextView = (TextView) listItemView.findViewById(R.id.ques_num);


        quesnumTextView.setText(currentWord.getMquestion_num());

        TextView quesTextView = (TextView) listItemView.findViewById(R.id.ques);

        quesTextView.setText(currentWord.getMquestion());

        TextView optiona = (TextView) listItemView.findViewById(R.id.optionA);

        optiona.setText(currentWord.getMoptionA());

        TextView optionb = (TextView) listItemView.findViewById(R.id.optionB);

        optionb.setText(currentWord.getMoptionB());

        TextView optionc = (TextView) listItemView.findViewById(R.id.optionC);

        optionc.setText(currentWord.getMoptionC());

        TextView optiond = (TextView) listItemView.findViewById(R.id.optionD);
        optiond.setText(currentWord.getMoptionD());

        TextView correctans = (TextView) listItemView.findViewById(R.id.correctans);

        correctans.setText(currentWord.getMcorrectansr());

        Button save=(Button)listItemView.findViewById(R.id.save);
        RadioGroup radioGroup=listItemView.findViewById(R.id.radioUser);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedid=radioGroup.getCheckedRadioButtonId();
                if(checkedid==-1){
                    //some operation
                }else{
                    findradiobutton(checkedid);
                }

            }

            private void findradiobutton(int checkedid) {
                switch (checkedid){
                    case R.id.optionA:
                        //correctans.setText("a");
                        if(currentWord.getMcorrectansr()=="1"){
                            //some operation

                        }
                        break;
                    case R.id.optionB:
                        //correctans.setText("b");
                        if (currentWord.getMcorrectansr()=="2"){
                            //some operation

                        }
                        break;
                    case R.id.optionC:
                        //correctans.setText("c");
                        if(currentWord.getMcorrectansr()=="3"){
                            //some operation

                        }
                        break;
                    case R.id.optionD:
                        //correctans.setText("d");
                        if (currentWord.getMcorrectansr()=="4"){
                            //some operation

                        }
                        break;

                }
            }


        });


        return listItemView;
    }

}
