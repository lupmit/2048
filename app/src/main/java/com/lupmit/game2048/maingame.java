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

    public int vuottrai(customAdapter customAdapter) {
        int diem = 0;
        for(int i = 3;i>=0; i--) {
            for(int j = 3; j>=0; j--) {
                int so = mangXL[i][j];
                if(so == 0)
                    continue;
                else {
                    for(int k = j-1; k>=0; k--) {
                        int sonew = mangXL[i][k];
                        if(sonew == 0)
                            continue;
                        else if(sonew == so) {
                            if(so!=0)
                                diem += 1;
                            mangXL[i][j] = so *2;
                            chuyendoi();
                            customAdapter.notifyDataSetChanged();
                            mangXL[i][k] = 0;
                            chuyendoi();
                            customAdapter.notifyDataSetChanged();
                            break;
                        }else
                            break;
                    }
                }
            }
        }

        for(int i = 3;i>=0; i--) {
            for(int j = 3; j>=0; j--) {
                int so = mangXL[i][j];
                if (so == 0) {
                    for(int k = j-1; k>=0; k--) {
                        int sonew = mangXL[i][k];
                        if (sonew == 0)
                            continue;
                        else {
                            mangXL[i][j] = mangXL[i][k];
                            chuyendoi();
                            customAdapter.notifyDataSetChanged();
                            mangXL[i][k] = 0;
                            chuyendoi();
                            customAdapter.notifyDataSetChanged();
                            break;
                        }
                    }
                }
            }
        }
        taoso();
        return diem;
    }
    public int vuotphai(customAdapter customAdapter) {
        int diem = 0;
        for(int i = 0;i<4; i++) {
            for(int j = 0; j<4; j++) {
                int so = mangXL[i][j];
                if(so == 0)
                    continue;
                else {
                    for(int k = j+1; k<4; k++) {
                        int sonew = mangXL[i][k];
                        if(sonew == 0)
                            continue;
                        else if(sonew == so) {
                            if(so!=0)
                            diem += 1;
                            mangXL[i][j] = so *2;
                            customAdapter.notifyDataSetChanged();
                            mangXL[i][k] = 0;
                            customAdapter.notifyDataSetChanged();
                            break;
                        }else
                            break;
                    }
                }
            }
        }

        for(int i = 0;i<4; i++) {
            for(int j = 0; j<4; j++) {
                int so = mangXL[i][j];
                if (so == 0) {
                    for(int k = j+1; k<4; k++) {
                        int sonew = mangXL[i][k];
                        if (sonew == 0)
                            continue;
                        else {
                            mangXL[i][j] = mangXL[i][k];
                            customAdapter.notifyDataSetChanged();
                            mangXL[i][k] = 0;
                            customAdapter.notifyDataSetChanged();
                            break;
                        }
                    }
                }
            }
        }
        taoso();
        chuyendoi();
        return diem;
    }
    public int vuotlen(customAdapter customAdapter) {
        int diem = 0;
        for(int i = 0;i< 4; i++) {
            for(int j = 0; j<4; j++) {
                int so = mangXL[j][i];
                if(so == 0)
                    continue;
                else {
                    for(int k = j+1; k<4; k++) {
                        int sonew = mangXL[k][i];
                        if(sonew == 0)
                            continue;
                        else if(sonew == so) {
                            if(so!=0)
                            diem+= 1;
                            mangXL[j][i] = so *2;
                            customAdapter.notifyDataSetChanged();
                            mangXL[k][i] = 0;
                            customAdapter.notifyDataSetChanged();
                            break;
                        }else
                            break;
                    }
                }
            }
        }

        for(int i = 0;i< 4; i++) {
            for (int j = 0; j < 4; j++) {
                int so = mangXL[j][i];
                if (so == 0) {
                    for(int k = j+1; k<4; k++) {
                        int sonew = mangXL[k][i];
                        if (sonew == 0)
                            continue;
                        else {
                            mangXL[j][i] = mangXL[k][i];
                            customAdapter.notifyDataSetChanged();
                            mangXL[k][i] = 0;
                            customAdapter.notifyDataSetChanged();
                            break;
                        }
                    }
                }
            }
        }
        taoso();
        chuyendoi();
        return diem;
    }
    public int vuotxuong(customAdapter customAdapter) {
        int diem = 0;
        for(int i = 3;i>=0; i--) {
            for(int j = 3; j>=0; j--) {
                int so = mangXL[j][i];
                if(so == 0)
                    continue;
                else {
                    for(int k = j-1; k>=0; k--) {
                        int sonew = mangXL[k][i];
                        if(sonew == 0)
                            continue;
                        else if(sonew == so) {
                            if(so!=0)
                            diem += 1;
                            mangXL[j][i] = so *2;
                            customAdapter.notifyDataSetChanged();
                            mangXL[k][i] = 0;
                            customAdapter.notifyDataSetChanged();
                            break;
                        }else
                            break;
                    }
                }
            }
        }

        for(int i = 3;i>=0; i--) {
            for(int j = 3; j>=0; j--) {
                int so = mangXL[j][i];
                if (so == 0) {
                    for(int k = j-1; k>=0; k--) {
                        int sonew = mangXL[k][i];
                        if (sonew == 0)
                            continue;
                        else {
                            mangXL[j][i] = mangXL[k][i];
                            customAdapter.notifyDataSetChanged();
                            mangXL[k][i] = 0;
                            customAdapter.notifyDataSetChanged();
                            break;
                        }
                    }
                }
            }
        }
        taoso();
        chuyendoi();
        return diem;
    }
}
