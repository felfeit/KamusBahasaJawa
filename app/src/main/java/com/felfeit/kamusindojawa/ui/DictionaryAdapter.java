package com.felfeit.kamusindojawa.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.felfeit.kamusindojawa.databinding.ItemDictionaryBinding;
import com.felfeit.kamusindojawa.models.DictionaryModel;

public class DictionaryAdapter extends ListAdapter<DictionaryModel, DictionaryAdapter.DictionaryViewHolder> {

    public DictionaryAdapter() {
        super(DIFF_CALLBACK);
    }

    // DiffUtil Callback
    private static final DiffUtil.ItemCallback<DictionaryModel> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull DictionaryModel oldItem,
                        @NonNull DictionaryModel newItem
                ) {
                    return oldItem.getId() == newItem.getId(); // Bandingkan ID
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull DictionaryModel oldItem,
                        @NonNull DictionaryModel newItem
                ) {
                    return oldItem.getJawa().equals(newItem.getJawa()) &&
                            oldItem.getIndonesia().equals(newItem.getIndonesia());
                }
            };

    // ViewHolder
    public static class DictionaryViewHolder extends RecyclerView.ViewHolder {
        private final ItemDictionaryBinding binding;

        public DictionaryViewHolder(ItemDictionaryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(DictionaryModel model) {
            binding.tvWordLeft.setText(model.getJawa());
            binding.tvWordRight.setText(model.getIndonesia());
        }
    }

    @NonNull
    @Override
    public DictionaryAdapter.DictionaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDictionaryBinding binding = ItemDictionaryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DictionaryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DictionaryAdapter.DictionaryViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
