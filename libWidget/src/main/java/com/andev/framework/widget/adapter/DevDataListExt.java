package com.andev.framework.widget.adapter;

import java.util.LinkedHashMap;
import java.util.List;

import com.andev.framework.data.DevPage;
import com.andev.framework.data.multiselect.IMultiSelectEdit;

/**
 * detail: DataManager List Extend
 *
 * @author Ttt
 * <pre>
 *     在 {@link DevDataList} 基础上添加 {@link DevDataAdapterExt2} 功能
 * </pre>
 */
public abstract class DevDataListExt<T>
        extends DevDataList<T>
        implements IMultiSelectEdit<DevDataListExt<T>> {

    public DevDataListExt() {
    }

    public DevDataListExt(DevPage.PageConfig pageConfig) {
        super(pageConfig);
    }

    public DevDataListExt(
            int page,
            int pageSize
    ) {
        super(page, pageSize);
    }

    public DevDataListExt(DevPage<T> page) {
        super(page);
    }

    // ======================
    // = DevDataAdapterExt2 =
    // ======================

    // =============
    // = 对外公开方法 =
    // =============

    // 是否通知适配器 ( 通用: 如多选操作后是否通知适配器 )
    protected boolean isNotifyAdapter = true;

    /**
     * 是否通知适配器 ( 通用: 如多选操作后是否通知适配器 )
     *
     * @return {@code true} yes, {@code false} no
     */
    public boolean isNotifyAdapter() {
        return isNotifyAdapter;
    }

    /**
     * 设置是否通知适配器 ( 通用: 如多选操作后是否通知适配器 )
     *
     * @param notifyAdapter {@code true} yes, {@code false} no
     * @return {@link DevDataListExt}
     */
    public DevDataListExt<T> setNotifyAdapter(boolean notifyAdapter) {
        isNotifyAdapter = notifyAdapter;
        return this;
    }

    // ====================
    // = IMultiSelectEdit =
    // ====================

    // 是否编辑状态
    protected boolean isEdit;

    @Override
    public boolean isEditState() {
        return isEdit;
    }

    @Override
    public DevDataListExt<T> setEditState(boolean isEdit) {
        this.isEdit = isEdit;
        if (isNotifyAdapter) mAssist.notifyDataChanged();
        return this;
    }

    @Override
    public DevDataListExt<T> toggleEditState() {
        return setEditState(!isEdit);
    }

    @Override
    public DevDataListExt<T> clearSelectAll() {
        mMultiSelectMap.clearSelects();
        if (isNotifyAdapter) mAssist.notifyDataChanged();
        return this;
    }

    @Override
    public boolean isSelectAll() {
        int size = mMultiSelectMap.getSelectSize();
        if (size == 0) return false;
        return getItemCount() == size;
    }

    @Override
    public boolean isSelect() {
        return mMultiSelectMap.isSelect();
    }

    @Override
    public boolean isNotSelect() {
        return !mMultiSelectMap.isSelect();
    }

    @Override
    public int getSelectSize() {
        return mMultiSelectMap.getSelectSize();
    }

    @Override
    public int getDataCount() {
        return getItemCount();
    }

    // =

    @Override
    public DevDataListExt<T> selectAll() {
        LinkedHashMap<String, T> maps = new LinkedHashMap<>();
        for (int i = 0, len = getDataCount(); i < len; i++) {
            T item = getDataItem(i);
            maps.put(getMultiSelectKey(item, i), item);
        }
        mMultiSelectMap.putSelects(maps);
        if (isNotifyAdapter) mAssist.notifyDataChanged();
        return this;
    }

    @Override
    public DevDataListExt<T> inverseSelect() {
        if (isNotSelect()) return selectAll();

        List<String> keys = mMultiSelectMap.getSelectKeys();

        LinkedHashMap<String, T> maps = new LinkedHashMap<>();
        for (int i = 0, len = getDataCount(); i < len; i++) {
            T item = getDataItem(i);
            maps.put(getMultiSelectKey(item, i), item);
        }
        mMultiSelectMap.putSelects(maps);

        for (String key : keys) {
            mMultiSelectMap.unselect(key);
        }
        if (isNotifyAdapter) mAssist.notifyDataChanged();
        return this;
    }

    // =

    /**
     * 获取多选标记 Key
     * <pre>
     *     用于 {@link #selectAll()}、{@link #inverseSelect()}
     * </pre>
     *
     * @param item     泛型实体类 Item
     * @param position 索引
     * @return 多选标记 Key
     */
    public abstract String getMultiSelectKey(
            T item,
            int position
    );
}