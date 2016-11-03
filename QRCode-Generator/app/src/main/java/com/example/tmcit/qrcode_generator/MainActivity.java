package com.example.tmcit.qrcode_generator;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    private static int cnt = 0;

    int lightnum = 0;
    int route_flag = 0;

    TextView textCnt;
    Bitmap qrCodeBitmap;
    ImageView imageView1;
    EditText editText;
    EditText editText_start;
    EditText editText_destination;


    public static int destinationId;//目的地場所ID
    public static int ceilingLightId;//天井照明ID
    public static String language;//地図の言語

    public static Direction direction;//経路情報
    List<Route> route;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);









        imageView1 = (ImageView)findViewById(R.id.imageView);
        editText = (EditText) findViewById(R.id.editText);
        editText_start = (EditText) findViewById(R.id.editText_start);
        editText_destination = (EditText) findViewById(R.id.editText_destination);

        viewQR(cnt);

        Button btn_m = (Button)findViewById(R.id.button);
        btn_m.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editText.selectAll();
                // エディットテキストのテキストを取得します
                String text = editText.getText().toString();
                cnt = Integer.valueOf(text);
                cnt--;
                viewQR(cnt);
            }
        });








        Button btn_p = (Button)findViewById(R.id.button2);
        btn_p.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                editText.selectAll();
                // エディットテキストのテキストを取得します
                String text = editText.getText().toString();
                cnt = Integer.valueOf(text);
                cnt++;


                viewQR(cnt);
            }
        });


        Button btn_n = (Button)findViewById(R.id.button3);
        btn_n.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                editText.selectAll();
                // エディットテキストのテキストを取得します
                String text = editText.getText().toString();

                cnt = Integer.valueOf(text);
                viewQR(cnt);
            }
        });






        Button btn_prev = (Button)findViewById(R.id.button_prev);
        btn_prev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(route_flag == 1 && lightnum != 0){
                    lightnum--;
                    viewQR(route.get(0).steps.get(lightnum).start.lightId);

                }else{
                    Toast.makeText(MainActivity.this,"エラー:出発地です。", Toast.LENGTH_LONG).show();
                }

            }
        });

        Button btn_next = (Button)findViewById(R.id.button_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(route_flag == 1 && lightnum != route.get(0).steps.size() - 1){
                    lightnum++;
                    viewQR(route.get(0).steps.get(lightnum).start.lightId);

                }else{
                    Toast.makeText(MainActivity.this,"エラー:目的地です。", Toast.LENGTH_LONG).show();
                }


            }
        });



        Button btn_generate = (Button)findViewById(R.id.button_generate);
        btn_generate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    String text_start = editText_start.getText().toString();
                    String text_destination = editText_destination.getText().toString();

                    ceilingLightId = Integer.valueOf(text_start);
                    destinationId = Integer.valueOf(text_destination);
                    FujitsuChizaiAPI.Direction.getDirection(ceilingLightId, destinationId, PlaceMarkType.Light, PlaceMarkType.Place, new AsyncCallback() {
                        @Override
                        public void onComplete(String result) {
                            if (result == null) return;
                            try {
                                Log.d("Direction", result);
                                direction = ParseJson.parseDirection(result);
                                route_flag = 1;
                                route = direction.routes;
                                Log.d("Direction Routes = ", String.valueOf(direction.routes.size()));
                                Log.d("Direction route[0]Steps", String.valueOf(route.get(0).steps.size()));
                                for (int i = 0; i < route.get(0).steps.size(); i++) {
                                    Log.d("Direction", String.valueOf(route.get(0).steps.get(i).start.lightId));
                                }
                                viewQR(route.get(0).steps.get(0).start.lightId);
                                lightnum = 0;
                            } catch (Exception e) {
                            }
                        }
                    });

                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"照明ID,目的地IDを入力してください。", Toast.LENGTH_LONG).show();

                }
            }
        });





    }



    private Bitmap createQRCode(String contents)
    {
        Bitmap qrBitmap = null;
        try {
            // QRコードの生成
            QRCodeWriter qrcodewriter = new QRCodeWriter();
            BitMatrix qrBitMatrix = qrcodewriter.encode(contents,
                    BarcodeFormat.QR_CODE,
                    300,
                    300);

            qrBitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
            qrBitmap.setPixels(this.createDot(qrBitMatrix), 0, 300, 0, 0, 300, 300);
        }
        catch(Exception ex)
        {
            // エンコード失敗
            Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();
        }
        finally
        {
            return qrBitmap;
        }
    }

    // ドット単位の判定
    private int[] createDot(BitMatrix qrBitMatrix)
    {
        // 縦幅・横幅の取得
        int width = qrBitMatrix.getWidth();
        int height = qrBitMatrix.getHeight();
        // 枠の生成
        int[] pixels = new int[width * height];

        // データが存在するところを黒にする
        for (int y = 0; y < height; y++)
        {
            // ループ回数盤目のOffsetの取得
            int offset = y * width;
            for (int x = 0; x < width; x++)
            {
                // データが存在する場合
                if (qrBitMatrix.get(x, y))
                {
                    pixels[offset + x] = Color.BLACK;
                }
                else
                {
                    pixels[offset + x] = Color.WHITE;
                }
            }
        }
        // 結果を返す
        return pixels;
    }

    void viewQR(int num){
        String st_num = String.valueOf(num);
        editText.setText(st_num);
        qrCodeBitmap = createQRCode(st_num);
        // QRCodeの作成に成功した場合
        if (qrCodeBitmap != null)
        {
            // 結果をImageViewに表示
            imageView1.setImageBitmap(qrCodeBitmap);
        }
    }

}

