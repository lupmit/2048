package com.lupmit.game2048;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

public class maingame {
    private static maingame maingame;
    private int[] mangMau;
    private ArrayList<Integer> arrSo = new ArrayList<>();
    int [][] mangXL = new int[4][4];
    int [][] mangXL_bf = new int[4][4];

    static {
        maingame = new maingame();
    }

    public static maingame getDatagame() {
        return maingame;
    }

    public void Init(Context context) {
        for(int i = 0;i< 4; i++) {
            for(int j = 0; j<4; j++) {
                mangXL[i][j] = 0;
                mangXL_bf[i][j] = 0;
                arrSo.add(0);
            }
        }

        TypedArray td = context.getResources().obtainTypedArray(R.array.maucuaso);
        mangMau = new int[td.length()];
        for(int i =0; i<td.length();i++) {
            mangMau[i] = td.getColor(i,0);

        }
        td.recycle();
        taoso();
        chuyendoi();
    }

    public ArrayList<Integer> getArrSo() {
        return arrSo;
    }

    public int color(int so) {
        if(so == 0) {
            return Color.parseColor("#3d2963");
        } else {
            int s = (int) (Math.log(so)/ Math.log(2));
            return mangMau[s-1];
        }
    }

    public void taoso() {
        Random r = new Random();
        int so0 = 0;
        for(int i = 0; i<16; i++) {
            if(arrSo.get(i) == 0) {
                so0 ++;
            }
        }
        int so0tao;
        if(so0 > 1) {
            so0tao = r.nextInt(2)+1;
        }else
        if(so0 == 1) {
            so0tao = 1;
        }else
            so0tao = 0;
        while(so0tao!=0) {
            int i = r.nextInt(4), j= r.nextInt(4);
            if(mangXL[i][j] == 0) {
                mangXL[i][j] = 2;
                so0tao --;
            }
        }
    }

    public void chuyendoi() {
        arrSo.clear();
        for(int i = 0;i< 4; i++) {
            for(int j = 0; j<4; j++) {
                arrSo.add(mangXL[i][j]);
            }
        }
    }

    public void coppyMang() {
        for(int i = 0;i< 4; i++) {
            for(int j = 0; j<4; j++) {
                mangXL_bf[i][j] = mangXL[i][j];
            }
        }
    }

    public void back_to_bf() {
        for(int i = 0;i< 4; i++) {
            for(int j = 0; j<4; j++) {
                mangXL[i][j] = mangXL_bf[i][j];
            }
        }
        chuyendoi();
    }

    public int vuottrai() {
        coppyMang();
        int hang,cot,cot_,diem = 0;
        for (hang=0;hang<4;hang++)
            for(cot=3;cot>0;cot--)
                for(cot_=3;cot_>0;cot_--)
                    if ((mangXL[hang][cot_] == 0) && (mangXL[hang][cot_-1]!=0)){
                        mangXL[hang][cot_] = mangXL[hang][cot_-1];
                        mangXL[hang][cot_-1] = 0;
                    }

        for (hang=0;hang<4;hang++)
            for (cot=3;cot>0;cot--)
                if (mangXL[hang][cot] == mangXL[hang][cot-1] && mangXL[hang][cot]!=0){  //cong don cac o lien nhau co cung gia tri
                    mangXL[hang][cot] += mangXL[hang][cot];
                    mangXL[hang][cot-1] = 0;
                    diem+=1;
                }
        for (hang=0;hang<4;hang++)
            for(cot=3;cot>0;cot--)
                for(cot_=3;cot_>0;cot_--)
                    if(mangXL[hang][cot_] == 0){
                        mangXL[hang][cot_] = mangXL[hang][cot_-1];
                        mangXL[hang][cot_-1] = 0;
                    }
        taoso();
        chuyendoi();
        return diem;
    }
    public int vuotphai() {
        coppyMang();
        int hang,cot,cot_,diem = 0;
        for (hang=0;hang<4;hang++)
            for(cot=0;cot<3;cot++)
                for(cot_=0;cot_ <3;cot_++)
                    if ((mangXL[hang][cot_] == 0) && (mangXL[hang][cot_+1]!=0)){
                        mangXL[hang][cot_] = mangXL[hang][cot_+1];
                        mangXL[hang][cot_+1] = 0;
                    }
        for (hang=0;hang<4;hang++)
            for (cot=0;cot<3;cot++)
                if (mangXL[hang][cot] == mangXL[hang][cot+1] && mangXL[hang][cot]!=0){  //cong don cac o lien nhau co cung gia tri
                    mangXL[hang][cot] += mangXL[hang][cot];
                    mangXL[hang][cot+1] = 0;
                    diem += 1;
                }
        for (hang=0;hang<4;hang++)
            for(cot=0;cot<3;cot++)
                for(cot_=0;cot_ <3;cot_++)
                    if(mangXL[hang][cot_] == 0){
                        mangXL[hang][cot_] = mangXL[hang][cot_+1];
                        mangXL[hang][cot_+1] = 0;
                    }
        taoso();
        chuyendoi();
        return diem;
    }

    public int vuotlen() {
        coppyMang();
        int hang,cot,hang_, diem = 0;
        for (cot=0;cot<4;cot++)
            for(hang=0;hang<3;hang++)
                for(hang_=0;hang_<3;hang_++)
                    if((mangXL[hang_][cot]==0) && (mangXL[hang_+1][cot]!=0)){
                        mangXL[hang_][cot]=mangXL[hang_+1][cot];
                        mangXL[hang_+1][cot]=0;
                    }
        for (cot=0;cot<4;cot++)
            for (hang=0;hang<3;hang++)
                if (mangXL[hang][cot]==mangXL[hang+1][cot] && mangXL[hang][cot]!=0){    //cong don cac o lien nhau co cung gia tri
                    mangXL[hang][cot]+=mangXL[hang][cot];
                    mangXL[hang+1][cot]=0;
                    diem+=1;
                }
        for (cot=0;cot<4;cot++)
            for(hang=0;hang<3;hang++)
                for(hang_=0;hang_<3;hang_++)
                    if(mangXL[hang_][cot]==0){
                        mangXL[hang_][cot]=mangXL[hang_+1][cot];
                        mangXL[hang_+1][cot]=0;
                    }
        taoso();
        chuyendoi();
        return diem;
    }
    public int vuotxuong() {
        coppyMang();
        int hang,cot,hang_,diem = 0;
        for (cot=0;cot<4;cot++)
            for(hang=3;hang>0;hang--)
                for(hang_=3;hang_>0;hang_--)
                    if ((mangXL[hang_][cot]==0) && (mangXL[hang_-1][cot]!=0)){
                        mangXL[hang_][cot]=mangXL[hang_-1][cot];
                        mangXL[hang_-1][cot]=0;
                    }
        for (cot=0;cot<4;cot++)
            for (hang=3;hang>0;hang--)
                if (mangXL[hang][cot]==mangXL[hang-1][cot] && mangXL[hang][cot]!=0){
                    mangXL[hang][cot]+=mangXL[hang][cot];
                    mangXL[hang-1][cot]=0;
                    diem+=1;
                }
        for (cot=0;cot<4;cot++)
            for(hang=3;hang>0;hang--)
                for(hang_=3;hang_>0;hang_--)
                    if(mangXL[hang_][cot]==0){
                        mangXL[hang_][cot]=mangXL[hang_-1][cot];
                        mangXL[hang_-1][cot]=0;
                    }
        taoso();
        chuyendoi();
        return diem;
    }

     public boolean endgame(){
        int i, j ,otrong=0,cong=0;
        for (i=0;i<4;i++)
            for (j=0;j<4;j++)
                if (mangXL[i][j] ==0) otrong = 1;
        for (i=0;i<3;i++)
            for (j=0;j<3;j++)
                if ((mangXL[i][j]==mangXL[i+1][j]) || (mangXL[i][j]==mangXL[i][j+1]))
                    cong = 1;

        if ((otrong==0)&&(cong==0)) return true;
        else return false;
    }
}
