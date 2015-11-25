package dnbapps.lottotrac;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.text.InputType;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Arrays;

import de.greenrobot.event.EventBus;


public class MainActivity extends ActionBarActivity {
    public static String appName = "lottotrac: ";
    public static String MY_PREFS_NAME = "lottotracprefs";
    private Menu mainMenu;

    MyDFrag keys = null;




    Drawable[] winBackgroundTypes = new Drawable[11];

int screenHeight;
    int screenWidth;

    public EditText[] winningNumEditTexts;
    public EditText[] row1EditTexts;
    public EditText[] row2EditTexts;
    public EditText[] row3EditTexts;
    public EditText[] row4EditTexts;
    public EditText[] row5EditTexts;
    public EditText[] row6EditTexts;

    public EditText[][] editTexts = new EditText[7][10];

    int index = 0;
    int row = 0;
    int[][] state = new int[6][10];


    int[] ids = new int[]{R.id.num1,R.id.num2,R.id.num3,R.id.num4,R.id.num5,R.id.num6,R.id.num7,
            R.id.num8,R.id.num9,R.id.num10};

    int[] row1ids = new int[]{R.id.num1r1,R.id.num2r1,R.id.num3r1,R.id.num4r1,R.id.num5r1,R.id.num6r1,
            R.id.num7r1, R.id.num8r1, R.id.num9r1};

    int[] row2ids = new int[]{R.id.num1r2,R.id.num2r2,R.id.num3r2,R.id.num4r2,R.id.num5r2,R.id.num6r2,
            R.id.num7r2, R.id.num8r2, R.id.num9r2};

    int[] row3ids = new int[]{R.id.num1r3,R.id.num2r3,R.id.num3r3,R.id.num4r3,R.id.num5r3,R.id.num6r3,
            R.id.num7r3, R.id.num8r3, R.id.num9r3};
    int[] row4ids = new int[]{R.id.num1r4,R.id.num2r4,R.id.num3r4,R.id.num4r4,R.id.num5r4,R.id.num6r4,
            R.id.num7r4, R.id.num8r4, R.id.num9r4};
    int[] row5ids = new int[]{R.id.num1r5,R.id.num2r5,R.id.num3r5,R.id.num4r5,R.id.num5r5,R.id.num6r5,
            R.id.num7r5, R.id.num8r5, R.id.num9r5};
    int[] row6ids = new int[]{R.id.num1r6,R.id.num2r6,R.id.num3r6,R.id.num4r6,R.id.num5r6,R.id.num6r6,
            R.id.num7r6, R.id.num8r6, R.id.num9r6};








    public SharedPreferences prefs;

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//
//        Resources res = getResources();
//        Drawable logo = res.getDrawable(R.mipmap.ic_launcher);
//

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setTitle("");
        //getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.paste_clear_button_style));




        Bitmap bm = BitmapFactory.decodeResource(this.getResources(),R.drawable.bonusimg);
        bm = getResizedBitmap(bm, 140, 77);
        ImageView img = (ImageView)findViewById(R.id.bonusImage);

        img.setImageBitmap(bm);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);


        int numVis = prefs.getInt("numVis",5);

        EventBus.getDefault().register(this);

        initEditTextArrays();
        setNumOptions(numVis);
        LoadSavedValues();
for(int i = 0;i<6;i++) {
    for (int z = 0; z < 9; z++) {

        state[i][z] = 0;

    }
}

        winBackgroundTypes[0] = getResources().getDrawable(R.drawable.text_style_match);
        winBackgroundTypes[1] = getResources().getDrawable(R.drawable.text_style_win1);

        winBackgroundTypes[2] = getResources().getDrawable(R.drawable.text_style_win2);
        winBackgroundTypes[3] = getResources().getDrawable(R.drawable.text_style_win3);
        winBackgroundTypes[4] = getResources().getDrawable(R.drawable.text_style_win4);
        winBackgroundTypes[5] = getResources().getDrawable(R.drawable.text_style_win5);
        winBackgroundTypes[6] = getResources().getDrawable(R.drawable.text_style_win6);
        winBackgroundTypes[7] = getResources().getDrawable(R.drawable.text_style_win7);
        winBackgroundTypes[8] = getResources().getDrawable(R.drawable.text_style_win8);
        winBackgroundTypes[9] = getResources().getDrawable(R.drawable.text_style_win9);
        winBackgroundTypes[10] = getResources().getDrawable(R.drawable.text_style_win_bonus);




        addListeners();
        highlightCellsAndSort();




    }



    public void paste(){
        EditText[] editTextsTemp = null;
        if(row == 0){
            editTextsTemp = winningNumEditTexts;
        }else if(row == 1){
            editTextsTemp = row1EditTexts;
        }
        else if(row == 2){
            editTextsTemp = row2EditTexts;
        }
        else if(row == 3){
            editTextsTemp = row3EditTexts;
        }
        else if(row == 4){
            editTextsTemp = row4EditTexts;
        }else if(row == 5){
            editTextsTemp = row5EditTexts;
        }
        else if(row == 6){
            editTextsTemp = row6EditTexts;
        }

        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        if(editTextsTemp!=null && clipboard.getText()!=null) {
            String txt = clipboard.getText().toString();
            if (txt != null && txt != "") {
                ArrayList<Integer> ints = new ArrayList<Integer>();
                String[] strs = txt.split(" ");
                for (int i = 0; i < strs.length && i < 9; i++) {
                    ints.add(i, -1);
                    try {
                        ints.set(i, Integer.parseInt(strs[i]));
                    } catch (Exception e) {
                        ints.set(i, -1);
                    }
                }
                int val = 0;
                for (int i = 0; i < ints.size() && i < 9; i++) {
                    val = ints.get(i);
                    if (val < 0)
                        continue;
                    editTextsTemp[i].setText(String.valueOf(val));
                }
            } else {
                //TODO: TOAST
            }
        }else{
            //TODO: TOAST
        }

    }

    public void clear(){
        EditText[] editTextsTemp = null;
        if(row == 0){
            editTextsTemp = winningNumEditTexts;
        }else if(row == 1){
            editTextsTemp = row1EditTexts;
        }
        else if(row == 2){
            editTextsTemp = row2EditTexts;
        }
        else if(row == 3){
            editTextsTemp = row3EditTexts;
        }
        else if(row == 4){
            editTextsTemp = row4EditTexts;
        }else if(row == 5){
            editTextsTemp = row5EditTexts;
        }
        else if(row == 6){
            editTextsTemp = row6EditTexts;
        }
        if(editTextsTemp!=null){
            int max = 9;
            if(row == 0)
                max = 10;
            for(int i = 0; i < max; i++){
                editTextsTemp[i].setText("");
            }
        }
    }

    public void onEvent(MyMsg msg){
        //Log.w("lotto", " event recieved");

        String number = "";

        if(msg.num.equals("Paste")){
            paste();
            highlightCellsAndSort();
            saveState();
            return;
        }else if(msg.num.equals("Clear")){
            clear();
            highlightCellsAndSort();
            saveState();
            return;
        }

        if(!msg.num.equalsIgnoreCase("X")){
            number=msg.num;
        }


        int pos = prefs.getInt("numVis",5);
for(int i = 0; i < pos && !number.equals(""); i++){
    if(row!=0 && i >= pos-1)
        continue;
    if(editTexts[row][i].getText().toString().equals(number))
        return;
}

        editTexts[row][index].setText(number);


        highlightCellsAndSort();

        saveState();

        flash();
    }

    public void saveState(){
        prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();


            for(int i = 0; i < winningNumEditTexts.length; i++) {
                editor.putString("info:" + 0 + "," + i, winningNumEditTexts[i].getText().toString());
                if(i==9)
                    continue;
                editor.putString("info:" + 1 + "," + i, row1EditTexts[i].getText().toString());
                editor.putString("info:" + 2 + "," + i, row2EditTexts[i].getText().toString());
                editor.putString("info:" + 3 + "," + i, row3EditTexts[i].getText().toString());
                editor.putString("info:" + 4 + "," + i, row4EditTexts[i].getText().toString());
                editor.putString("info:"+5+","+i,row5EditTexts[i].getText().toString());
                editor.putString("info:"+6+","+i,row6EditTexts[i].getText().toString());
            }
        editor.apply();
    }


    public void flash(){




            Animation animation = new AlphaAnimation(1,(float).2);

            animation.setDuration(350); // duration - half a second
            animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
            animation.setRepeatCount(Animation.ABSOLUTE); // Repeat animation infinitely
            animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in


        if (row == 0) {


            winningNumEditTexts[index].startAnimation(animation);
        }
        if (row == 1) {

            row1EditTexts[index].startAnimation(animation);

        }
        if (row == 2) {
            row2EditTexts[index].startAnimation(animation);
        }
        if (row == 3) {
            row3EditTexts[index].startAnimation(animation);
        }
        if (row == 4) {
            row4EditTexts[index].startAnimation(animation);
        }
        if (row == 5) {
            row5EditTexts[index].startAnimation(animation);
        }
        if (row == 6) {

            row6EditTexts[index].startAnimation(animation);
        }


    }


    public void highlightCellsAndSort(){
        EditText[][] grid;
        grid = new EditText[7][10];
        grid[0]=row1EditTexts;
        grid[1] = row2EditTexts;
        grid[2] = row3EditTexts;
        grid[3] = row4EditTexts;
        grid[4] = row5EditTexts;
        grid[5] = row6EditTexts;
        grid[6] = winningNumEditTexts;

        int number;

        int pos = prefs.getInt("numVis",5);
        ArrayList<Integer> winningNums = new ArrayList<>();
        String readStr = "";
        int[] values = new int[pos];
        String str1 = "";
        for(int z = 0; z < 7; z++){
            values = new int[pos-1];
            for(int y = 0; y < pos-1; y++){

                str1 = grid[z][y].getText().toString();
                if(str1.equals(""))
                    str1 =  "-1";
                values[y] = Integer.parseInt(str1);
            }
            Arrays.sort(values);
            int dist = 0;
            for( EditText a:grid[z]) {
                if (dist >= pos || (dist == (pos-1)))
                    break;
                dist++;
                a.setText("");
            }
            int position = 0;
            for(int i = 0; i < values.length; i++){
                if(values[i]==-1)
                    continue;
                grid[z][position].setText(values[i]+"");
                position++;
            }
        }


        for(int d = 0; d < pos-1; d++){
            readStr = winningNumEditTexts[d].getText().toString();
            if(readStr.equals(""))
                readStr="-1";
            winningNums.add(Integer.parseInt(readStr));
        }
        String readStr2 = "";

        int[] matchesPerRow = new int[6];
        for(int num : matchesPerRow) {
            num = 0;
        }

        for(int i = 0; i < 6; i++) {
            EditText[] subGrid = grid[i];
            for (int c = 0; c < pos-1; c++) {
                readStr2 = subGrid[c].getText().toString();
                if (readStr2.equals(""))
                    readStr2 = "-2";
                number = Integer.parseInt(readStr2);

                if (winningNums.contains(number)) {
                    matchesPerRow[i] = matchesPerRow[i] + 1;
                }
            }
        }

        boolean lightMainRow = false;
        for(int nums:matchesPerRow) {
            if (nums >= pos-1)
                lightMainRow = true;

        }


        String bonusStr = winningNumEditTexts[pos-1].getText().toString();
        int bonusNum;
        if(bonusStr.equals("")){
            bonusNum = -1;
        }else {
            bonusNum = Integer.parseInt(winningNumEditTexts[pos - 1].getText().toString());
        }


        for(int i = 0; i < 6; i++){
            EditText[] subGrid = grid[i];
            for(int c = 0; c < pos-1; c ++){

                readStr2=subGrid[c].getText().toString();
                if(readStr2.equals(""))
                    readStr2 = "-2";
                number = Integer.parseInt(readStr2);

                if(winningNums.contains(number) || bonusNum==number) {
                    state[i][c] = 1;
                    Drawable x = winBackgroundTypes[3];
                    if(bonusNum==number){
                        x = winBackgroundTypes[10];
                    }

                    subGrid[c].setBackground(x);
                }else {
                    state[i][c] = 0;
                    subGrid[c].setBackground(getResources().getDrawable(R.drawable.text_style_match));
                    //subGrid[c].setBackground(oldBackground);
                }

            }
        }


        for (EditText box : winningNumEditTexts)
            box.setBackground(winBackgroundTypes[0]);
        if(lightMainRow) {
            for (int i = 0; i < pos-1; i++)
                winningNumEditTexts[i].setBackground(winBackgroundTypes[10]);
        }
    }







    public void addListeners(){

        for(int i = 0; i < 10; i ++){
            winningNumEditTexts[i].setOnClickListener(new MyOnClickListener(0, i, this));
            winningNumEditTexts[i].setOnLongClickListener(new MyOnLongClickListener(0, i, this));
if(i==9)
    continue;
            row1EditTexts[i].setOnClickListener(new MyOnClickListener(1, i, this));
            row1EditTexts[i].setOnLongClickListener(new MyOnLongClickListener(1, i, this));

            row2EditTexts[i].setOnClickListener(new MyOnClickListener(2, i, this));
            row2EditTexts[i].setOnLongClickListener(new MyOnLongClickListener(2, i, this));

            row3EditTexts[i].setOnClickListener(new MyOnClickListener(3, i, this));
            row3EditTexts[i].setOnLongClickListener(new MyOnLongClickListener(3, i, this));

            row4EditTexts[i].setOnClickListener(new MyOnClickListener(4, i, this));
            row4EditTexts[i].setOnLongClickListener(new MyOnLongClickListener(4, i, this));

            row5EditTexts[i].setOnClickListener(new MyOnClickListener(5, i, this));
            row5EditTexts[i].setOnLongClickListener(new MyOnLongClickListener(5, i, this));

            row6EditTexts[i].setOnClickListener(new MyOnClickListener(6, i, this));
            row6EditTexts[i].setOnLongClickListener(new MyOnLongClickListener(6,i,this));
        }


    }



    public void initEditTextArrays(){
        winningNumEditTexts = new EditText[10];
        row1EditTexts = new EditText[9];
        row2EditTexts = new EditText[9];
        row3EditTexts = new EditText[9];
        row4EditTexts = new EditText[9];
        row5EditTexts = new EditText[9];
        row6EditTexts = new EditText[9];
        for(int i = 0; i < 10; i++){
            winningNumEditTexts[i] = (EditText) findViewById(ids[i]);

            if(i == 9)
                break;

            row1EditTexts[i] = (EditText) findViewById(row1ids[i]);

            row2EditTexts[i] = (EditText) findViewById(row2ids[i]);

            row3EditTexts[i] = (EditText) findViewById(row3ids[i]);

            row4EditTexts[i] = (EditText) findViewById(row4ids[i]);

            row5EditTexts[i] = (EditText) findViewById(row5ids[i]);

            row6EditTexts[i] = (EditText) findViewById(row6ids[i]);
        }


        editTexts[0]= winningNumEditTexts;
        editTexts[1]= row1EditTexts;
        editTexts[2]= row2EditTexts;
        editTexts[3]= row3EditTexts;
        editTexts[4]=row4EditTexts;
        editTexts[5]= row5EditTexts;
        editTexts[6]= row6EditTexts;



        for(EditText a : winningNumEditTexts)
            a.setInputType(InputType.TYPE_NULL);
        for(EditText a : row1EditTexts)
            a.setInputType(InputType.TYPE_NULL);
        for(EditText a : row2EditTexts)
            a.setInputType(InputType.TYPE_NULL);
        for(EditText a : row3EditTexts)
            a.setInputType(InputType.TYPE_NULL);
        for(EditText a : row4EditTexts)
            a.setInputType(InputType.TYPE_NULL);
        for(EditText a : row5EditTexts)
            a.setInputType(InputType.TYPE_NULL);
        for(EditText a : row6EditTexts)
            a.setInputType(InputType.TYPE_NULL);


    }

    public void LoadSavedValues(){
        prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        for(int i = 0; i < winningNumEditTexts.length; i++)
            winningNumEditTexts[i].setText(prefs.getString("info:0," + i,""));
        for(int i = 0; i < row1EditTexts.length; i ++)
            row1EditTexts[i].setText(prefs.getString("info:1," + i,""));

        for(int i = 0; i < row2EditTexts.length; i ++)
            row2EditTexts[i].setText(prefs.getString("info:2," + i,""));

        for(int i = 0; i < row3EditTexts.length; i ++)
            row3EditTexts[i].setText(prefs.getString("info:3," + i,""));

        for(int i = 0; i < row4EditTexts.length; i ++)
            row4EditTexts[i].setText(prefs.getString("info:4," + i,""));

        for(int i = 0; i < row5EditTexts.length; i ++)
            row5EditTexts[i].setText(prefs.getString("info:5," + i,""));

        for(int i = 0; i < row6EditTexts.length; i ++)
            row6EditTexts[i].setText(prefs.getString("info:6," + i,""));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mainMenu = menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.getItem(0).setEnabled(false);
        menu.setGroupCheckable(101,true,true);
        for(int i = 1; i < 7; i++)
            menu.getItem(i).setCheckable(true);

        menu.getItem(prefs.getInt("numVis",5)-4).setChecked(true);
        return true;
    }

    public void setNumOptions(int numOpts){
        for(int i = 3; i < numOpts;i++){

            winningNumEditTexts[i].setVisibility(View.VISIBLE);

if(i!=9) {
    row1EditTexts[i].setVisibility(View.VISIBLE);

    row2EditTexts[i].setVisibility(View.VISIBLE);

    row3EditTexts[i].setVisibility(View.VISIBLE);

    row4EditTexts[i].setVisibility(View.VISIBLE);

    row5EditTexts[i].setVisibility(View.VISIBLE);

    row6EditTexts[i].setVisibility(View.VISIBLE);
}
        }

        for(int i = numOpts-1; i < 10; i++){
if(i!=9) {
    row1EditTexts[i].setVisibility(View.GONE);

    row2EditTexts[i].setVisibility(View.GONE);

    row3EditTexts[i].setVisibility(View.GONE);

    row4EditTexts[i].setVisibility(View.GONE);

    row5EditTexts[i].setVisibility(View.GONE);

    row6EditTexts[i].setVisibility(View.GONE);
}

            if(i== numOpts-1)
                continue;
            winningNumEditTexts[i].setVisibility(View.GONE);
        }



long spacing = Math.round(screenWidth/(float)3.6);

        int num = Math.round(spacing/numOpts);
        ImageView img = (ImageView)findViewById(R.id.bonusImage);
        img.setPadding(0,0,num+15,0);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        SharedPreferences.Editor editor = prefs.edit();

        if (id == R.id.action_settings) {
            return true;
        }
        for(int i = 1; i <= 7; i++)
                mainMenu.getItem(i).setChecked(false);

        if (id == R.id.fourOption){
            mainMenu.getItem(1).setChecked(true);
            editor.putInt("numVis",5);
            editor.apply();
            setNumOptions(5);
        }
        else if(id == R.id.fiveOption){

            mainMenu.getItem(2).setChecked(true);
            editor.putInt("numVis",6);
            editor.apply();
            setNumOptions(6);
        }else if(id == R.id.sixOption){
            mainMenu.getItem(3).setChecked(true);
            editor.putInt("numVis",7);
            editor.apply();
            setNumOptions(7);
        }else if(id == R.id.sevenOption){
            mainMenu.getItem(4).setChecked(true);
            editor.putInt("numVis",8);
            editor.apply();
            setNumOptions(8);
        }else if(id == R.id.eightOption) {
            mainMenu.getItem(5).setChecked(true);
            editor.putInt("numVis", 9);
            editor.apply();
            setNumOptions(9);
        }else if(id == R.id.nineOption){
            mainMenu.getItem(5).setChecked(true);
            editor.putInt("numVis",10);
            editor.apply();
            setNumOptions(10);
        }else if(id == R.id.aboutOption){
            Intent intent = new Intent(this, AboutDisclaimerActivity.class);
            startActivity(intent);
        }




        highlightCellsAndSort();
        saveState();
        return super.onOptionsItemSelected(item);
    }



    public class MyOnLongClickListener implements View.OnLongClickListener{
        int row;
        int index;
        MainActivity parentActivity;

        public boolean onLongClick(View arg0){
            parentActivity.row = row;
            parentActivity.index = index;
            MyPasteFrag dfrag = new MyPasteFrag();

            Bundle bundle1 = new Bundle();
            dfrag.setArguments(bundle1);
            dfrag.show(getFragmentManager(), "dialog");

            return false;

        }

        public MyOnLongClickListener(int row, int index, MainActivity context){

        parentActivity = context;
            this.row = row;
            this.index = index;


        }
    }

    public class MyOnClickListener implements View.OnClickListener{
        int row;
        int index;
        MainActivity parentActivity;
        public MyOnClickListener(int row1, int index1, MainActivity parent){
            this.row = row1;
            this.index = index1;
            this.parentActivity = parent;

        }
        public void onClick(View arg0){
            Log.w("Lottotrac: ", row + ", " + index);


            // show a dialog fragment in the normal way

            parentActivity.row = row;
            parentActivity.index = index;
            if(keys!=null)
                keys.dismiss();
            if(keys == null) {
                keys = new MyDFrag();
                Bundle bundle1 = new Bundle();
                keys.setArguments(bundle1);

                keys.show(getFragmentManager(), "dialog");
            }else{
                Log.w(appName,"Showing old fragment!");
                keys.show(getFragmentManager(), "dialog");
            }




            //parentActivity.showMyDialog(row, index);
        }
    }


    public static class MyPasteFrag extends DialogFragment implements View.OnClickListener {
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setStyle(STYLE_NO_TITLE, getTheme());
            setCancelable(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View myCurView = inflater.inflate(R.layout.fragment_my_paste_layout, container, false);
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            Bundle args = getArguments();
            setListeners(myCurView);

            return myCurView;
        }

        public void onClick(View v) {
            String buttonText = ((Button) v).getText().toString();
            if (buttonText.equals("Paste"))
                EventBus.getDefault().post(new MyMsg("Paste", 0, 0));
            else
                EventBus.getDefault().post(new MyMsg("Clear", 0, 0));

            getFragmentManager().beginTransaction().remove(MyPasteFrag.this).addToBackStack(null).commit();
        }

        public void setListeners(View myCurView) {
            myCurView.findViewById(R.id.paste).setOnClickListener(this);
            myCurView.findViewById(R.id.clear_row).setOnClickListener(this);
        }
    }




}

