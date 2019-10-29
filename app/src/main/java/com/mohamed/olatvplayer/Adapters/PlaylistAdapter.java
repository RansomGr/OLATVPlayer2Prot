package com.mohamed.olatvplayer.Adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mohamed.olatvplayer.Models.M3UItem;
import com.mohamed.olatvplayer.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ItemHolder> implements Filterable {

    private final Context mContext;
    private final LayoutInflater mInflater;
    private List<M3UItem> mItem = new ArrayList<>();


    PlaylistAdapter(Context c) {
        mContext = c;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View sView = mInflater.inflate(R.layout.item_playlist, parent, false);
        return new ItemHolder(sView);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, final int position) {
        final M3UItem item = mItem.get(position);
        if (item != null) {
            holder.update(item);
        }
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }

    void update(List<M3UItem> _list) {
        this.mItem = _list;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() { //TODO search it on github
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mItem.clear();
                System.out.println("Size of result : "+ ((ArrayList<M3UItem>) results.values).size());
                mItem.addAll((ArrayList<M3UItem>) results.values);
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (!(constraint.length() == 0)) {
                    System.out.println("mItem count : "+mItem.size());
                    mItem.clear();
                    final String filtePatt = constraint.toString().toLowerCase().trim();
                    for (M3UItem itm : mItem) {
                        if (itm.getItemName().toLowerCase().contains(filtePatt)) {
                            Toast.makeText(mContext, "Google", Toast.LENGTH_SHORT).show();
                            mItem.add(itm);
                        }
                        mItem.add(itm);
                    }
                }
                results.values = mItem;
                results.count = mItem.size();
                return results;
            }
        };
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final PackageManager pm = mContext.getPackageManager();

        TextView name;
        ImageView cImg;

        ItemHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            name = view.findViewById(R.id.item_name);
            cImg = view.findViewById(R.id.cimg);
        }

        void update(final M3UItem item) { //  here we bind data to view

        }

        public void onClick(View v) { // here we Play the Item

        }

        void playy(String urli, String channel) {
            try {
                Uri videoUri = Uri.parse(urli);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(videoUri, "application/x-mpegURL");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.mxtech.videoplayer.ad");
                intent.putExtra("title", channel);
                mContext.startActivity(intent);
            } catch (Exception ignored) {
            }
        }

        void playerNotFound(Context context, String title, String message) {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(context);
            localBuilder.setTitle(title);
            localBuilder.setMessage(message);
            localBuilder.setPositiveButton("Install", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                    try {
                        mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.mxtech.videoplayer.ad")));
                    } catch (ActivityNotFoundException ActivityNotFoundException) {
                        mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.mxtech.videoplayer.ad&hl=en")));
                    }
                }
            });
//        localBuilder.setNegativeButton("Google", new DialogInterface.OnClickListener()
//        {
//            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
//        });
            //localBuilder.setCancelable(false);
            localBuilder.create().show();
        }
    }
}
