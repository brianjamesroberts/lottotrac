package dnbapps.lottotrac;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import de.greenrobot.event.EventBus;
import dnbapps.lottotrac.MyMsg;
import dnbapps.lottotrac.R;

public class MyDFrag extends DialogFragment implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, getTheme());

        setCancelable(true);
    }
    public void onClick(View v){
        String buttonText = ((Button)v).getText().toString();



        EventBus.getDefault().post(new MyMsg(buttonText,0,0));
        getFragmentManager().beginTransaction().remove(MyDFrag.this).addToBackStack(null).commit();
        //   EventBus.getDefault().postSticky(new MyMsg(buttonNum, row, index));
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myCurView = inflater.inflate(R.layout.fragment_my_dialog, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Bundle args = getArguments();
        setListeners(myCurView);

        return myCurView;
    }


    public void setListeners(View myCurView){
        myCurView.findViewById(R.id.bclear).setOnClickListener(this);
        myCurView.findViewById(R.id.b1).setOnClickListener(this);
        myCurView.findViewById(R.id.b2).setOnClickListener(this);
        myCurView.findViewById(R.id.b3).setOnClickListener(this);
        myCurView.findViewById(R.id.b4).setOnClickListener(this);
        myCurView.findViewById(R.id.b5).setOnClickListener(this);
        myCurView.findViewById(R.id.b6).setOnClickListener(this);
        myCurView.findViewById(R.id.b7).setOnClickListener(this);
        myCurView.findViewById(R.id.b8).setOnClickListener(this);
        myCurView.findViewById(R.id.b9).setOnClickListener(this);
        myCurView.findViewById(R.id.b10).setOnClickListener(this);
        myCurView.findViewById(R.id.b11).setOnClickListener(this);
        myCurView.findViewById(R.id.b12).setOnClickListener(this);
        myCurView.findViewById(R.id.b13).setOnClickListener(this);
        myCurView.findViewById(R.id.b14).setOnClickListener(this);
        myCurView.findViewById(R.id.b15).setOnClickListener(this);
        myCurView.findViewById(R.id.b16).setOnClickListener(this);
        myCurView.findViewById(R.id.b17).setOnClickListener(this);
        myCurView.findViewById(R.id.b18).setOnClickListener(this);
        myCurView.findViewById(R.id.b19).setOnClickListener(this);
        myCurView.findViewById(R.id.b20).setOnClickListener(this);
        myCurView.findViewById(R.id.b21).setOnClickListener(this);
        myCurView.findViewById(R.id.b22).setOnClickListener(this);
        myCurView.findViewById(R.id.b23).setOnClickListener(this);
        myCurView.findViewById(R.id.b24).setOnClickListener(this);
        myCurView.findViewById(R.id.b25).setOnClickListener(this);
        myCurView.findViewById(R.id.b26).setOnClickListener(this);
        myCurView.findViewById(R.id.b27).setOnClickListener(this);
        myCurView.findViewById(R.id.b28).setOnClickListener(this);
        myCurView.findViewById(R.id.b29).setOnClickListener(this);
        myCurView.findViewById(R.id.b30).setOnClickListener(this);
        myCurView.findViewById(R.id.b31).setOnClickListener(this);
        myCurView.findViewById(R.id.b32).setOnClickListener(this);
        myCurView.findViewById(R.id.b33).setOnClickListener(this);
        myCurView.findViewById(R.id.b34).setOnClickListener(this);
        myCurView.findViewById(R.id.b35).setOnClickListener(this);
        myCurView.findViewById(R.id.b36).setOnClickListener(this);
        myCurView.findViewById(R.id.b37).setOnClickListener(this);
        myCurView.findViewById(R.id.b38).setOnClickListener(this);
        myCurView.findViewById(R.id.b39).setOnClickListener(this);
        myCurView.findViewById(R.id.b40).setOnClickListener(this);
        myCurView.findViewById(R.id.b41).setOnClickListener(this);
        myCurView.findViewById(R.id.b42).setOnClickListener(this);
        myCurView.findViewById(R.id.b43).setOnClickListener(this);
        myCurView.findViewById(R.id.b44).setOnClickListener(this);
        myCurView.findViewById(R.id.b45).setOnClickListener(this);
        myCurView.findViewById(R.id.b46).setOnClickListener(this);
        myCurView.findViewById(R.id.b47).setOnClickListener(this);
        myCurView.findViewById(R.id.b48).setOnClickListener(this);
        myCurView.findViewById(R.id.b49).setOnClickListener(this);
        myCurView.findViewById(R.id.b50).setOnClickListener(this);
        myCurView.findViewById(R.id.b51).setOnClickListener(this);
        myCurView.findViewById(R.id.b52).setOnClickListener(this);
        myCurView.findViewById(R.id.b53).setOnClickListener(this);
        myCurView.findViewById(R.id.b54).setOnClickListener(this);
        myCurView.findViewById(R.id.b55).setOnClickListener(this);
        myCurView.findViewById(R.id.b56).setOnClickListener(this);
        myCurView.findViewById(R.id.b57).setOnClickListener(this);
        myCurView.findViewById(R.id.b58).setOnClickListener(this);
        myCurView.findViewById(R.id.b59).setOnClickListener(this);
        myCurView.findViewById(R.id.b60).setOnClickListener(this);
        myCurView.findViewById(R.id.b61).setOnClickListener(this);
        myCurView.findViewById(R.id.b62).setOnClickListener(this);
        myCurView.findViewById(R.id.b63).setOnClickListener(this);
        myCurView.findViewById(R.id.b64).setOnClickListener(this);
        myCurView.findViewById(R.id.b65).setOnClickListener(this);
        myCurView.findViewById(R.id.b66).setOnClickListener(this);
        myCurView.findViewById(R.id.b67).setOnClickListener(this);
        myCurView.findViewById(R.id.b68).setOnClickListener(this);
        myCurView.findViewById(R.id.b69).setOnClickListener(this);
        myCurView.findViewById(R.id.b70).setOnClickListener(this);
        myCurView.findViewById(R.id.b71).setOnClickListener(this);
        myCurView.findViewById(R.id.b72).setOnClickListener(this);
        myCurView.findViewById(R.id.b73).setOnClickListener(this);
        myCurView.findViewById(R.id.b74).setOnClickListener(this);
        myCurView.findViewById(R.id.b75).setOnClickListener(this);
        myCurView.findViewById(R.id.b76).setOnClickListener(this);
        myCurView.findViewById(R.id.b77).setOnClickListener(this);
        myCurView.findViewById(R.id.b78).setOnClickListener(this);
        myCurView.findViewById(R.id.b79).setOnClickListener(this);
        myCurView.findViewById(R.id.b80).setOnClickListener(this);
        myCurView.findViewById(R.id.b81).setOnClickListener(this);
        myCurView.findViewById(R.id.b82).setOnClickListener(this);
        myCurView.findViewById(R.id.b83).setOnClickListener(this);
        myCurView.findViewById(R.id.b84).setOnClickListener(this);
        myCurView.findViewById(R.id.b85).setOnClickListener(this);
        myCurView.findViewById(R.id.b86).setOnClickListener(this);
        myCurView.findViewById(R.id.b87).setOnClickListener(this);
        myCurView.findViewById(R.id.b88).setOnClickListener(this);
        myCurView.findViewById(R.id.b89).setOnClickListener(this);
        myCurView.findViewById(R.id.b90).setOnClickListener(this);
        myCurView.findViewById(R.id.b91).setOnClickListener(this);
        myCurView.findViewById(R.id.b92).setOnClickListener(this);
        myCurView.findViewById(R.id.b93).setOnClickListener(this);
        myCurView.findViewById(R.id.b94).setOnClickListener(this);
        myCurView.findViewById(R.id.b95).setOnClickListener(this);
        myCurView.findViewById(R.id.b96).setOnClickListener(this);
        myCurView.findViewById(R.id.b97).setOnClickListener(this);
        myCurView.findViewById(R.id.b98).setOnClickListener(this);
        myCurView.findViewById(R.id.b99).setOnClickListener(this);
    }
}
