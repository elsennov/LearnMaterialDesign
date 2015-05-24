package com.elsennovraditya.materialdesign;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by elsen on 5/10/15.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;

    private static final int TYPE_ITEM = 1;

    private final Context mContext;

    private final MenuHeader mMenuHeader;

    private final MenuItem[] mMenuItem;

    private final ViewHolder.OnMenuClickListener mMenuClickListener;

    public MenuAdapter(Context context, MenuHeader menuHeader, MenuItem[] menuItem,
            ViewHolder.OnMenuClickListener menuClickListener) {
        mContext = context;
        mMenuHeader = menuHeader;
        mMenuItem = menuItem;
        mMenuClickListener = menuClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent,
                    false);
            ViewHolder viewHolder = new ViewHolder(v, viewType, mMenuClickListener);
            return viewHolder;
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_header, parent,
                    false);
            ViewHolder vhHeader = new ViewHolder(v, viewType, mMenuClickListener);
            return vhHeader;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder.holderId == TYPE_ITEM) {
            holder.menuTitle.setText(mMenuItem[position - 1].getTitle());
            Picasso.with(mContext)
                    .load(mMenuItem[position - 1].getIcon())
                    .into(holder.menuIcon);
        } else {
            Picasso.with(mContext)
                    .load(mMenuHeader.getIcon())
                    .into(holder.userIcon);
            holder.userName.setText(mMenuHeader.getName());
            holder.userEmail.setText(mMenuHeader.getEmail());
        }
    }

    @Override
    public int getItemCount() {
        return mMenuItem.length + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderPosition(position)) {
            return TYPE_HEADER;
        }

        return TYPE_ITEM;
    }

    private boolean isHeaderPosition(int position) {
        return position == 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener {

        private final OnMenuClickListener menuClickListener;

        TextView userEmail;

        TextView userName;

        ImageView userIcon;

        TextView menuTitle;

        ImageView menuIcon;

        int holderId;

        public ViewHolder(View itemView, int viewType, OnMenuClickListener menuClickListener) {
            super(itemView);
            this.menuClickListener = menuClickListener;
            if (viewType == TYPE_ITEM) {
                menuTitle = (TextView) itemView.findViewById(R.id.menu_title);
                menuIcon = (ImageView) itemView.findViewById(R.id.menu_icon);
                itemView.setOnClickListener(this);
                holderId = TYPE_ITEM;
            } else {
                userEmail = (TextView) itemView.findViewById(R.id.email);
                userName = (TextView) itemView.findViewById(R.id.name);
                userIcon = (ImageView) itemView.findViewById(R.id.user_icon);
                userIcon.setOnClickListener(this);
                userIcon.setOnLongClickListener(this);
                holderId = TYPE_HEADER;
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.user_icon:
                    menuClickListener.onHeaderIconClicked();
                    break;
                case R.id.menu_item_root:
                    menuClickListener.onMenuClicked(getPosition());
                    break;
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (v.getId() == R.id.user_icon) {
                menuClickListener.onHeaderIconLongClicked(userIcon);
                return true;
            } else {
                return false;
            }
        }

        public interface OnMenuClickListener {

            void onHeaderIconClicked();

            void onHeaderIconLongClicked(View headerIcon);

            void onMenuClicked(int position);

        }
    }

}
