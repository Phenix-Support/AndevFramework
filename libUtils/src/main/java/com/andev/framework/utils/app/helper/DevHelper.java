package com.andev.framework.utils.app.helper;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.PopupWindow;

import androidx.annotation.IntRange;
import androidx.annotation.RawRes;
import androidx.annotation.RequiresPermission;
import androidx.fragment.app.DialogFragment;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Locale;

import com.andev.framework.utils.app.CleanUtils;
import com.andev.framework.utils.app.ClickUtils;
import com.andev.framework.utils.app.ClipboardUtils;
import com.andev.framework.utils.app.DialogUtils;
import com.andev.framework.utils.app.EditTextUtils;
import com.andev.framework.utils.app.HandlerUtils;
import com.andev.framework.utils.app.KeyBoardUtils;
import com.andev.framework.utils.app.LanguageUtils;
import com.andev.framework.utils.app.ListenerUtils;
import com.andev.framework.utils.app.permission.storage.MediaStoreUtils;
import com.andev.framework.utils.app.NotificationUtils;
import com.andev.framework.utils.app.ResourceUtils;
import com.andev.framework.utils.app.ScreenUtils;
import com.andev.framework.utils.app.SizeUtils;
import com.andev.framework.utils.app.VibrationUtils;
import com.andev.framework.utils.app.anim.AnimationUtils;
import com.andev.framework.utils.app.image.BitmapUtils;
import com.andev.framework.utils.app.image.ImageUtils;
import com.andev.framework.utils.app.timer.DevTimer;
import com.andev.framework.utils.common.CloseUtils;
import com.andev.framework.utils.common.HttpURLConnectionUtils;
import com.andev.framework.utils.common.assist.TimeKeeper;
import com.andev.framework.utils.common.assist.record.FileRecordUtils;
import com.andev.framework.utils.common.assist.record.RecordConfig;

/**
 * detail: Dev ????????????????????? Helper ???
 *
 * @author Ttt
 * <pre>
 *     ?????? DevApp ?????????????????????
 *     <p></p>
 *     DevApp Api
 *     @see <a href="https://github.com/afkT/DevUtils/blob/master/lib/DevApp/README.md"/>
 * </pre>
 */
public final class DevHelper {

    // TimeKeeper
    private final TimeKeeper mTimeKeeper = new TimeKeeper();
    // DevHelper
    private static final DevHelper HELPER = new DevHelper();

    /**
     * ???????????? DevHelper
     *
     * @return {@link DevHelper}
     */
    public static DevHelper get() {
        return HELPER;
    }

    // ==========
    // = Helper =
    // ==========

    /**
     * ?????? ViewHelper
     *
     * @return {@link ViewHelper}
     */
    public ViewHelper viewHelper() {
        return ViewHelper.get();
    }

    /**
     * ?????? DevHelper
     *
     * @return {@link DevHelper}
     */
    public DevHelper devHelper() {
        return HELPER;
    }

    /**
     * ?????? QuickHelper
     *
     * @param target ?????? View
     * @return {@link QuickHelper}
     */
    public QuickHelper quickHelper(final View target) {
        return QuickHelper.get(target);
    }

    // ===========
    // = Handler =
    // ===========

    /**
     * ???????????? Handler ???????????????
     *
     * @param runnable ??????????????????
     * @return {@link DevHelper}
     */
    public DevHelper postRunnable(final Runnable runnable) {
        HandlerUtils.postRunnable(runnable);
        return this;
    }

    /**
     * ???????????? Handler ?????????????????????
     *
     * @param runnable    ??????????????????
     * @param delayMillis ????????????
     * @return {@link DevHelper}
     */
    public DevHelper postRunnable(
            final Runnable runnable,
            final long delayMillis
    ) {
        HandlerUtils.postRunnable(runnable, delayMillis);
        return this;
    }

    /**
     * ???????????? Handler ?????????????????????
     *
     * @param runnable    ??????????????????
     * @param delayMillis ????????????
     * @param number      ????????????
     * @param interval    ????????????
     * @return {@link DevHelper}
     */
    public DevHelper postRunnable(
            final Runnable runnable,
            final long delayMillis,
            final int number,
            final int interval
    ) {
        HandlerUtils.postRunnable(runnable, delayMillis, number, interval);
        return this;
    }

    /**
     * ???????????? Handler ?????????????????????
     *
     * @param runnable      ??????????????????
     * @param delayMillis   ????????????
     * @param number        ????????????
     * @param interval      ????????????
     * @param onEndListener ????????????
     * @return {@link DevHelper}
     */
    public DevHelper postRunnable(
            final Runnable runnable,
            final long delayMillis,
            final int number,
            final int interval,
            final HandlerUtils.OnEndListener onEndListener
    ) {
        HandlerUtils.postRunnable(runnable, delayMillis, number, interval, onEndListener);
        return this;
    }

    /**
     * ???????????? Handler ???????????????
     *
     * @param runnable ?????????????????????
     * @return {@link DevHelper}
     */
    public DevHelper removeRunnable(final Runnable runnable) {
        HandlerUtils.removeRunnable(runnable);
        return this;
    }

    // ================
    // = TimerManager =
    // ================

    /**
     * ???????????????
     *
     * @param timer {@link DevTimer}
     * @return {@link DevHelper}
     */
    public DevHelper startTimer(final DevTimer timer) {
        if (timer != null) timer.start();
        return this;
    }

    /**
     * ???????????????
     *
     * @param timer {@link DevTimer}
     * @return {@link DevHelper}
     */
    public DevHelper closeTimer(final DevTimer timer) {
        if (timer != null) timer.stop();
        return this;
    }

    // ===============
    // = BitmapUtils =
    // ===============

    /**
     * Bitmap ????????????
     *
     * @param bitmap ???????????????
     * @return {@link DevHelper}
     */
    public DevHelper recycle(final Bitmap bitmap) {
        BitmapUtils.recycle(bitmap);
        return this;
    }

    // ==============
    // = ImageUtils =
    // ==============

    /**
     * ??????????????? SDCard ( JPEG )
     *
     * @param bitmap   ???????????????
     * @param filePath ????????????
     * @return {@link DevHelper}
     */
    public DevHelper saveBitmapToSDCardJPEG(
            final Bitmap bitmap,
            final String filePath
    ) {
        return saveBitmapToSDCard(bitmap, filePath, Bitmap.CompressFormat.JPEG, 100);
    }

    /**
     * ??????????????? SDCard ( JPEG )
     *
     * @param bitmap ???????????????
     * @param file   ????????????
     * @return {@link DevHelper}
     */
    public DevHelper saveBitmapToSDCardJPEG(
            final Bitmap bitmap,
            final File file
    ) {
        return saveBitmapToSDCard(bitmap, file, Bitmap.CompressFormat.JPEG, 100);
    }

    // =

    /**
     * ??????????????? SDCard ( JPEG )
     *
     * @param bitmap   ???????????????
     * @param filePath ????????????
     * @param quality  ??????
     * @return {@link DevHelper}
     */
    public DevHelper saveBitmapToSDCardJPEG(
            final Bitmap bitmap,
            final String filePath,
            @IntRange(from = 0, to = 100) final int quality
    ) {
        return saveBitmapToSDCard(bitmap, filePath, Bitmap.CompressFormat.JPEG, quality);
    }

    /**
     * ??????????????? SDCard ( JPEG )
     *
     * @param bitmap  ???????????????
     * @param file    ????????????
     * @param quality ??????
     * @return {@link DevHelper}
     */
    public DevHelper saveBitmapToSDCardJPEG(
            final Bitmap bitmap,
            final File file,
            @IntRange(from = 0, to = 100) final int quality
    ) {
        return saveBitmapToSDCard(bitmap, file, Bitmap.CompressFormat.JPEG, quality);
    }

    // =

    /**
     * ??????????????? SDCard ( PNG )
     *
     * @param bitmap   ???????????????
     * @param filePath ????????????
     * @return {@link DevHelper}
     */
    public DevHelper saveBitmapToSDCardPNG(
            final Bitmap bitmap,
            final String filePath
    ) {
        return saveBitmapToSDCard(bitmap, filePath, Bitmap.CompressFormat.PNG, 100);
    }

    /**
     * ??????????????? SDCard ( PNG )
     *
     * @param bitmap ???????????????
     * @param file   ????????????
     * @return {@link DevHelper}
     */
    public DevHelper saveBitmapToSDCardPNG(
            final Bitmap bitmap,
            final File file
    ) {
        return saveBitmapToSDCard(bitmap, file, Bitmap.CompressFormat.PNG, 100);
    }

    // =

    /**
     * ??????????????? SDCard ( PNG )
     *
     * @param bitmap   ???????????????
     * @param filePath ????????????
     * @param quality  ??????
     * @return {@link DevHelper}
     */
    public DevHelper saveBitmapToSDCardPNG(
            final Bitmap bitmap,
            final String filePath,
            @IntRange(from = 0, to = 100) final int quality
    ) {
        return saveBitmapToSDCard(bitmap, filePath, Bitmap.CompressFormat.PNG, quality);
    }

    /**
     * ??????????????? SDCard ( PNG )
     *
     * @param bitmap  ???????????????
     * @param file    ????????????
     * @param quality ??????
     * @return {@link DevHelper}
     */
    public DevHelper saveBitmapToSDCardPNG(
            final Bitmap bitmap,
            final File file,
            @IntRange(from = 0, to = 100) final int quality
    ) {
        return saveBitmapToSDCard(bitmap, file, Bitmap.CompressFormat.PNG, quality);
    }

    // =

    /**
     * ??????????????? SDCard ( WEBP )
     *
     * @param bitmap   ???????????????
     * @param filePath ????????????
     * @return {@link DevHelper}
     */
    public DevHelper saveBitmapToSDCardWEBP(
            final Bitmap bitmap,
            final String filePath
    ) {
        return saveBitmapToSDCard(bitmap, filePath, Bitmap.CompressFormat.WEBP, 100);
    }

    /**
     * ??????????????? SDCard ( WEBP )
     *
     * @param bitmap ???????????????
     * @param file   ????????????
     * @return {@link DevHelper}
     */
    public DevHelper saveBitmapToSDCardWEBP(
            final Bitmap bitmap,
            final File file
    ) {
        return saveBitmapToSDCard(bitmap, file, Bitmap.CompressFormat.WEBP, 100);
    }

    // =

    /**
     * ??????????????? SDCard ( WEBP )
     *
     * @param bitmap   ???????????????
     * @param filePath ????????????
     * @param quality  ??????
     * @return {@link DevHelper}
     */
    public DevHelper saveBitmapToSDCardWEBP(
            final Bitmap bitmap,
            final String filePath,
            @IntRange(from = 0, to = 100) final int quality
    ) {
        return saveBitmapToSDCard(bitmap, filePath, Bitmap.CompressFormat.WEBP, quality);
    }

    /**
     * ??????????????? SDCard ( WEBP )
     *
     * @param bitmap  ???????????????
     * @param file    ????????????
     * @param quality ??????
     * @return {@link DevHelper}
     */
    public DevHelper saveBitmapToSDCardWEBP(
            final Bitmap bitmap,
            final File file,
            @IntRange(from = 0, to = 100) final int quality
    ) {
        return saveBitmapToSDCard(bitmap, file, Bitmap.CompressFormat.WEBP, quality);
    }

    // =

    /**
     * ??????????????? SDCard
     *
     * @param bitmap   ???????????????
     * @param filePath ????????????
     * @param format   ??? Bitmap.CompressFormat.PNG
     * @param quality  ??????
     * @return {@link DevHelper}
     */
    public DevHelper saveBitmapToSDCard(
            final Bitmap bitmap,
            final String filePath,
            final Bitmap.CompressFormat format,
            @IntRange(from = 0, to = 100) final int quality
    ) {
        ImageUtils.saveBitmapToSDCard(bitmap, filePath, format, quality);
        return this;
    }

    /**
     * ??????????????? SDCard
     *
     * @param bitmap  ???????????????
     * @param file    ????????????
     * @param format  ??? Bitmap.CompressFormat.PNG
     * @param quality ??????
     * @return {@link DevHelper}
     */
    public DevHelper saveBitmapToSDCard(
            final Bitmap bitmap,
            final File file,
            final Bitmap.CompressFormat format,
            @IntRange(from = 0, to = 100) final int quality
    ) {
        ImageUtils.saveBitmapToSDCard(bitmap, file, format, quality);
        return this;
    }

    // =================
    // = EditTextUtils =
    // =================

    /**
     * ????????????????????????
     *
     * @param editText {@link EditText}
     * @param watcher  ????????????
     * @return {@link DevHelper}
     */
    public DevHelper addTextChangedListener(
            final EditText editText,
            final TextWatcher watcher
    ) {
        EditTextUtils.addTextChangedListener(editText, watcher);
        return this;
    }

    /**
     * ????????????????????????
     *
     * @param editText {@link EditText}
     * @param watcher  ????????????
     * @return {@link DevHelper}
     */
    public DevHelper removeTextChangedListener(
            final EditText editText,
            final TextWatcher watcher
    ) {
        EditTextUtils.removeTextChangedListener(editText, watcher);
        return this;
    }

    /**
     * ?????? KeyListener
     *
     * @param editText    {@link EditText}
     * @param keyListener {@link KeyListener}
     * @return {@link DevHelper}
     */
    public DevHelper setKeyListener(
            final EditText editText,
            final KeyListener keyListener
    ) {
        EditTextUtils.setKeyListener(editText, keyListener);
        return this;
    }

    /**
     * ?????? KeyListener
     *
     * @param editText {@link EditText}
     * @param accepted ?????????????????????, ???: 0123456789
     * @return {@link DevHelper}
     */
    public DevHelper setKeyListener(
            final EditText editText,
            final String accepted
    ) {
        EditTextUtils.setKeyListener(editText, accepted);
        return this;
    }

    /**
     * ?????? KeyListener
     *
     * @param editText {@link EditText}
     * @param accepted ?????????????????????
     * @return {@link DevHelper}
     */
    public DevHelper setKeyListener(
            final EditText editText,
            final char[] accepted
    ) {
        EditTextUtils.setKeyListener(editText, accepted);
        return this;
    }

    // ===================
    // = FileRecordUtils =
    // ===================

    /**
     * ????????????
     *
     * @param config ????????????????????????
     * @param logs   ??????????????????
     * @return {@link DevHelper}
     */
    public DevHelper record(
            final RecordConfig config,
            final Object... logs
    ) {
        FileRecordUtils.record(config, logs);
        return this;
    }

    // ==============
    // = CleanUtils =
    // ==============

    /**
     * ?????????????????? ( path /data/data/package/cache )
     *
     * @return {@link DevHelper}
     */
    public DevHelper cleanAppCache() {
        CleanUtils.cleanAppCache();
        return this;
    }

    /**
     * ?????????????????? ( path /data/data/package/files )
     *
     * @return {@link DevHelper}
     */
    public DevHelper cleanAppFiles() {
        CleanUtils.cleanAppFiles();
        return this;
    }

    /**
     * ????????????????????? ( path /data/data/package/databases )
     *
     * @return {@link DevHelper}
     */
    public DevHelper cleanAppDbs() {
        CleanUtils.cleanAppDbs();
        return this;
    }

    /**
     * ??????????????????????????? ( path /data/data/package/databases/dbName )
     *
     * @param dbName ????????????
     * @return {@link DevHelper}
     */
    public DevHelper cleanAppDbByName(final String dbName) {
        CleanUtils.cleanAppDbByName(dbName);
        return this;
    }

    /**
     * ???????????? SP ( path /data/data/package/shared_prefs )
     *
     * @return {@link DevHelper}
     */
    public DevHelper cleanAppSp() {
        CleanUtils.cleanAppSp();
        return this;
    }

    /**
     * ???????????? SP ( path /data/data/package/shared_prefs )
     *
     * @param spName SP ?????????
     * @return {@link DevHelper}
     */
    public DevHelper cleanAppSp(final String spName) {
        CleanUtils.cleanAppSp(spName);
        return this;
    }

    /**
     * ?????????????????? ( path /storage/emulated/0/android/data/package/cache )
     *
     * @return {@link DevHelper}
     */
    public DevHelper cleanCache() {
        CleanUtils.cleanCache();
        return this;
    }

    // =

    /**
     * ?????????????????????????????????
     * <pre>
     *     ??????????????????????????????, ???????????????????????????????????????
     * </pre>
     *
     * @param filePath ????????????
     * @return {@link DevHelper}
     */
    public DevHelper cleanCustomDir(final String filePath) {
        CleanUtils.cleanCustomDir(filePath);
        return this;
    }

    /**
     * ?????????????????????????????????
     * <pre>
     *     ??????????????????????????????, ???????????????????????????????????????
     * </pre>
     *
     * @param file ????????????
     * @return {@link DevHelper}
     */
    public DevHelper cleanCustomDir(final File file) {
        CleanUtils.cleanCustomDir(file);
        return this;
    }

    /**
     * ??????????????????????????????
     *
     * @param filePaths ??????????????????
     * @return {@link DevHelper}
     */
    public DevHelper cleanApplicationData(final String... filePaths) {
        CleanUtils.cleanApplicationData(filePaths);
        return this;
    }

    // ==================
    // = ClipboardUtils =
    // ==================

    /**
     * ????????????????????????
     *
     * @param text ??????
     * @return {@link DevHelper}
     */
    public DevHelper copyText(final CharSequence text) {
        ClipboardUtils.copyText(text);
        return this;
    }

    /**
     * ?????? URI ????????????
     *
     * @param uri {@link Uri}
     * @return {@link DevHelper}
     */
    public DevHelper copyUri(final Uri uri) {
        ClipboardUtils.copyUri(uri);
        return this;
    }

    /**
     * ????????????????????????
     *
     * @param intent {@link Intent}
     * @return {@link DevHelper}
     */
    public DevHelper copyIntent(final Intent intent) {
        ClipboardUtils.copyIntent(intent);
        return this;
    }

    // ===================
    // = MediaStoreUtils =
    // ===================

    /**
     * ????????????????????????
     *
     * @param filePath ????????????
     * @return {@link DevHelper}
     */
    public DevHelper notifyMediaStore(final String filePath) {
        MediaStoreUtils.notifyMediaStore(filePath);
        return this;
    }

    /**
     * ????????????????????????
     *
     * @param file ??????
     * @return {@link DevHelper}
     */
    public DevHelper notifyMediaStore(final File file) {
        MediaStoreUtils.notifyMediaStore(file);
        return this;
    }

    // ===============
    // = DialogUtils =
    // ===============

    /**
     * ?????? Dialog
     *
     * @param dialog {@link Dialog}
     * @param <T>    ??????
     * @return {@link DevHelper}
     */
    public <T extends Dialog> DevHelper showDialog(final T dialog) {
        DialogUtils.showDialog(dialog);
        return this;
    }

    /**
     * ?????? Dialog
     *
     * @param dialog {@link Dialog}
     * @return {@link DevHelper}
     */
    public DevHelper closeDialog(final Dialog dialog) {
        DialogUtils.closeDialog(dialog);
        return this;
    }

    /**
     * ???????????? Dialog
     *
     * @param dialogs {@link Dialog} ??????
     * @return {@link DevHelper}
     */
    public DevHelper closeDialogs(final Dialog... dialogs) {
        DialogUtils.closeDialogs(dialogs);
        return this;
    }

    // =

    /**
     * ?????? DialogFragment
     *
     * @param dialog {@link DialogFragment}
     * @return {@link DevHelper}
     */
    public DevHelper closeDialog(final DialogFragment dialog) {
        DialogUtils.closeDialog(dialog);
        return this;
    }

    /**
     * ???????????? DialogFragment
     *
     * @param dialogs {@link DialogFragment} ??????
     * @return {@link DevHelper}
     */
    public DevHelper closeDialogs(final DialogFragment... dialogs) {
        DialogUtils.closeDialogs(dialogs);
        return this;
    }

    // =

    /**
     * ?????? PopupWindow
     *
     * @param popupWindow {@link PopupWindow}
     * @return {@link DevHelper}
     */
    public DevHelper closePopupWindow(final PopupWindow popupWindow) {
        DialogUtils.closePopupWindow(popupWindow);
        return this;
    }

    /**
     * ???????????? PopupWindow
     *
     * @param popupWindows {@link PopupWindow} ??????
     * @return {@link DevHelper}
     */
    public DevHelper closePopupWindows(final PopupWindow... popupWindows) {
        DialogUtils.closePopupWindows(popupWindows);
        return this;
    }

    // =

    /**
     * ???????????? dialog
     *
     * @param dialog      {@link Dialog}
     * @param delayMillis ??????????????????
     * @param handler     {@link Handler}
     * @param <T>         ??????
     * @return {@link DevHelper}
     */
    public <T extends Dialog> DevHelper autoCloseDialog(
            final T dialog,
            final long delayMillis,
            final Handler handler
    ) {
        DialogUtils.autoCloseDialog(dialog, delayMillis, handler);
        return this;
    }

    /**
     * ???????????? DialogFragment
     *
     * @param dialog      {@link DialogFragment}
     * @param delayMillis ??????????????????
     * @param handler     {@link Handler}
     * @param <T>         ??????
     * @return {@link DevHelper}
     */
    public <T extends DialogFragment> DevHelper autoCloseDialog(
            final T dialog,
            final long delayMillis,
            final Handler handler
    ) {
        DialogUtils.autoCloseDialog(dialog, delayMillis, handler);
        return this;
    }

    /**
     * ???????????? PopupWindow
     *
     * @param popupWindow {@link PopupWindow}
     * @param delayMillis ??????????????????
     * @param handler     {@link Handler}
     * @param <T>         ??????
     * @return {@link DevHelper}
     */
    public <T extends PopupWindow> DevHelper autoClosePopupWindow(
            final T popupWindow,
            final long delayMillis,
            final Handler handler
    ) {
        DialogUtils.autoClosePopupWindow(popupWindow, delayMillis, handler);
        return this;
    }

    // =================
    // = KeyBoardUtils =
    // =================

    // ============================
    // = ????????? EditText ?????????????????? =
    // ============================

    /**
     * ???????????? View ???????????? EditText ?????? View OnTouchListener ??????
     *
     * @param view     {@link View}
     * @param activity {@link Activity}
     * @return {@link DevHelper}
     */
    public DevHelper judgeView(
            final View view,
            final Activity activity
    ) {
        KeyBoardUtils.judgeView(view, activity);
        return this;
    }

    /**
     * ???????????????????????????
     *
     * @param activity {@link Activity}
     * @param listener {@link KeyBoardUtils.OnSoftInputChangedListener}
     * @return {@link DevHelper}
     */
    public DevHelper registerSoftInputChangedListener(
            final Activity activity,
            final KeyBoardUtils.OnSoftInputChangedListener listener
    ) {
        KeyBoardUtils.registerSoftInputChangedListener(activity, listener);
        return this;
    }

    /**
     * ???????????????????????????
     *
     * @param activity {@link Activity}
     * @param listener {@link KeyBoardUtils.OnSoftInputChangedListener}
     * @return {@link DevHelper}
     */
    public DevHelper registerSoftInputChangedListener2(
            final Activity activity,
            final KeyBoardUtils.OnSoftInputChangedListener listener
    ) {
        KeyBoardUtils.registerSoftInputChangedListener2(activity, listener);
        return this;
    }

    // ============
    // = ??????????????? =
    // ============

    /**
     * ???????????????
     *
     * @return {@link DevHelper}
     */
    public DevHelper openKeyboard() {
        KeyBoardUtils.openKeyboard();
        return this;
    }

    /**
     * ?????????????????????
     *
     * @return {@link DevHelper}
     */
    public DevHelper openKeyboardDelay() {
        KeyBoardUtils.openKeyboardDelay();
        return this;
    }

    /**
     * ?????????????????????
     *
     * @param delayMillis ???????????? ( ?????? )
     * @return {@link DevHelper}
     */
    public DevHelper openKeyboardDelay(final int delayMillis) {
        KeyBoardUtils.openKeyboardDelay(delayMillis);
        return this;
    }

    // =

    /**
     * ???????????????
     *
     * @param editText {@link EditText}
     * @return {@link DevHelper}
     */
    public DevHelper openKeyboard(final EditText editText) {
        KeyBoardUtils.openKeyboard(editText);
        return this;
    }

    /**
     * ?????????????????????
     *
     * @param editText {@link EditText}
     * @return {@link DevHelper}
     */
    public DevHelper openKeyboardDelay(final EditText editText) {
        KeyBoardUtils.openKeyboardDelay(editText);
        return this;
    }

    /**
     * ?????????????????????
     *
     * @param editText    {@link EditText}
     * @param delayMillis ???????????? ( ?????? )
     * @return {@link DevHelper}
     */
    public DevHelper openKeyboardDelay(
            final EditText editText,
            final int delayMillis
    ) {
        KeyBoardUtils.openKeyboardDelay(editText, delayMillis);
        return this;
    }

    // ============
    // = ??????????????? =
    // ============

    /**
     * ???????????????
     *
     * @return {@link DevHelper}
     */
    public DevHelper closeKeyboard() {
        KeyBoardUtils.closeKeyboard();
        return this;
    }

    /**
     * ???????????????
     *
     * @param editText {@link EditText}
     * @return {@link DevHelper}
     */
    public DevHelper closeKeyboard(final EditText editText) {
        KeyBoardUtils.closeKeyboard(editText);
        return this;
    }

    /**
     * ???????????????
     *
     * @param activity {@link Activity}
     * @return {@link DevHelper}
     */
    public DevHelper closeKeyboard(final Activity activity) {
        KeyBoardUtils.closeKeyboard(activity);
        return this;
    }

    /**
     * ?????? dialog ??????????????????
     *
     * @param dialog {@link Dialog}
     * @return {@link DevHelper}
     */
    public DevHelper closeKeyboard(final Dialog dialog) {
        KeyBoardUtils.closeKeyboard(dialog);
        return this;
    }

    /**
     * ???????????????
     *
     * @param editText {@link EditText}
     * @param dialog   {@link Dialog}
     * @return {@link DevHelper}
     */
    public DevHelper closeKeyBoardSpecial(
            final EditText editText,
            final Dialog dialog
    ) {
        KeyBoardUtils.closeKeyBoardSpecial(editText, dialog);
        return this;
    }

    // ==========
    // = ???????????? =
    // ==========

    /**
     * ?????????????????????
     *
     * @param editText {@link EditText}
     * @param dialog   {@link Dialog}
     * @return {@link DevHelper}
     */
    public DevHelper closeKeyBoardSpecialDelay(
            final EditText editText,
            final Dialog dialog
    ) {
        KeyBoardUtils.closeKeyBoardSpecialDelay(editText, dialog);
        return this;
    }

    /**
     * ?????????????????????
     *
     * @param editText    {@link EditText}
     * @param dialog      {@link Dialog}
     * @param delayMillis ???????????? ( ?????? )
     * @return {@link DevHelper}
     */
    public DevHelper closeKeyBoardSpecialDelay(
            final EditText editText,
            final Dialog dialog,
            final int delayMillis
    ) {
        KeyBoardUtils.closeKeyBoardSpecialDelay(editText, dialog, delayMillis);
        return this;
    }

    // =

    /**
     * ?????????????????????
     *
     * @return {@link DevHelper}
     */
    public DevHelper closeKeyboardDelay() {
        KeyBoardUtils.closeKeyboardDelay();
        return this;
    }

    /**
     * ?????????????????????
     *
     * @param delayMillis ???????????? ( ?????? )
     * @return {@link DevHelper}
     */
    public DevHelper closeKeyboardDelay(final int delayMillis) {
        KeyBoardUtils.closeKeyboardDelay(delayMillis);
        return this;
    }

    /**
     * ?????????????????????
     *
     * @param editText {@link EditText}
     * @return {@link DevHelper}
     */
    public DevHelper closeKeyboardDelay(final EditText editText) {
        KeyBoardUtils.closeKeyboardDelay(editText);
        return this;
    }

    /**
     * ?????????????????????
     *
     * @param editText    {@link EditText}
     * @param delayMillis ???????????? ( ?????? )
     * @return {@link DevHelper}
     */
    public DevHelper closeKeyboardDelay(
            final EditText editText,
            final int delayMillis
    ) {
        KeyBoardUtils.closeKeyboardDelay(editText, delayMillis);
        return this;
    }

    /**
     * ?????????????????????
     *
     * @param activity {@link Activity}
     * @return {@link DevHelper}
     */
    public DevHelper closeKeyboardDelay(final Activity activity) {
        KeyBoardUtils.closeKeyboardDelay(activity);
        return this;
    }

    /**
     * ?????????????????????
     *
     * @param activity    {@link Activity}
     * @param delayMillis ???????????? ( ?????? )
     * @return {@link DevHelper}
     */
    public DevHelper closeKeyboardDelay(
            final Activity activity,
            final int delayMillis
    ) {
        KeyBoardUtils.closeKeyboardDelay(activity, delayMillis);
        return this;
    }

    /**
     * ?????????????????????
     *
     * @param dialog {@link Dialog}
     * @return {@link DevHelper}
     */
    public DevHelper closeKeyboardDelay(final Dialog dialog) {
        KeyBoardUtils.closeKeyboardDelay(dialog);
        return this;
    }

    /**
     * ?????????????????????
     *
     * @param dialog      {@link Dialog}
     * @param delayMillis ???????????? ( ?????? )
     * @return {@link DevHelper}
     */
    public DevHelper closeKeyboardDelay(
            final Dialog dialog,
            final int delayMillis
    ) {
        KeyBoardUtils.closeKeyboardDelay(dialog, delayMillis);
        return this;
    }

    // =================
    // = LanguageUtils =
    // =================

    /**
     * ?????????????????? (APP ?????????, ???????????? APP ?????? )
     *
     * @param context {@link Context}
     * @param locale  {@link Locale}
     * @return {@link DevHelper}
     */
    public DevHelper applyLanguage(
            final Context context,
            final Locale locale
    ) {
        LanguageUtils.applyLanguage(context, locale);
        return this;
    }

    /**
     * ?????????????????? (APP ?????????, ???????????? APP ?????? )
     *
     * @param context  {@link Context}
     * @param language ??????
     * @return {@link DevHelper}
     */
    public DevHelper applyLanguage(
            final Context context,
            final String language
    ) {
        LanguageUtils.applyLanguage(context, language);
        return this;
    }

    // =================
    // = ListenerUtils =
    // =================

    /**
     * ??????????????????
     *
     * @param onClickListener {@link View.OnClickListener}
     * @param views           View ??????
     * @return {@link DevHelper}
     */
    public DevHelper setOnClicks(
            final View.OnClickListener onClickListener,
            final View... views
    ) {
        ListenerUtils.setOnClicks(onClickListener, views);
        return this;
    }

    /**
     * ??????????????????
     *
     * @param onLongClickListener {@link View.OnLongClickListener}
     * @param views               View ??????
     * @return {@link DevHelper}
     */
    public DevHelper setOnLongClicks(
            final View.OnLongClickListener onLongClickListener,
            final View... views
    ) {
        ListenerUtils.setOnLongClicks(onLongClickListener, views);
        return this;
    }

    /**
     * ??????????????????
     *
     * @param onTouchListener {@link View.OnTouchListener}
     * @param views           View ??????
     * @return {@link DevHelper}
     */
    public DevHelper setOnTouchs(
            final View.OnTouchListener onTouchListener,
            final View... views
    ) {
        ListenerUtils.setOnTouchs(onTouchListener, views);
        return this;
    }

    /**
     * ???????????????????????????, ???????????????????????????????????????????????????
     *
     * @param view  ????????????????????? View
     * @param range ????????????
     * @return {@link DevHelper}
     */
    public DevHelper addTouchArea(
            final View view,
            final int range
    ) {
        ClickUtils.addTouchArea(view, range);
        return this;
    }

    /**
     * ???????????????????????????, ???????????????????????????????????????????????????
     *
     * @param view   ????????????????????? View
     * @param top    top range
     * @param bottom bottom range
     * @param left   left range
     * @param right  right range
     * @return {@link DevHelper}
     */
    public DevHelper addTouchArea(
            final View view,
            final int top,
            final int bottom,
            final int left,
            final int right
    ) {
        ClickUtils.addTouchArea(view, top, bottom, left, right);
        return this;
    }

    // =====================
    // = NotificationUtils =
    // =====================

    /**
     * ???????????? ( ?????????????????? )
     *
     * @return {@link DevHelper}
     */
    public DevHelper cancelAllNotification() {
        NotificationUtils.cancelAll();
        return this;
    }

    /**
     * ???????????? ( ??????????????? id ????????? )
     *
     * @param args ?????? id ??????
     * @return {@link DevHelper}
     */
    public DevHelper cancelNotification(final int... args) {
        NotificationUtils.cancel(args);
        return this;
    }

    /**
     * ???????????? ( ??????????????? id ????????? )
     *
     * @param tag ?????? TAG
     * @param id  ?????? id
     * @return {@link DevHelper}
     */
    public DevHelper cancelNotification(
            final String tag,
            final int id
    ) {
        NotificationUtils.cancel(tag, id);
        return this;
    }

    /**
     * ????????????
     *
     * @param id           ?????? id
     * @param notification {@link Notification}
     * @return {@link DevHelper}
     */
    public DevHelper notifyNotification(
            final int id,
            final Notification notification
    ) {
        NotificationUtils.notify(id, notification);
        return this;
    }

    /**
     * ????????????
     *
     * @param tag          ?????? TAG
     * @param id           ?????? id
     * @param notification {@link Notification}
     * @return {@link DevHelper}
     */
    public DevHelper notifyNotification(
            final String tag,
            final int id,
            final Notification notification
    ) {
        NotificationUtils.notify(tag, id, notification);
        return this;
    }

    // =================
    // = ResourceUtils =
    // =================

    /**
     * ?????? Assets ????????????????????????????????????
     *
     * @param fileName ?????????
     * @param file     ??????????????????
     * @return {@link DevHelper}
     */
    public DevHelper saveAssetsFormFile(
            final String fileName,
            final File file
    ) {
        ResourceUtils.saveAssetsFormFile(fileName, file);
        return this;
    }

    /**
     * ?????? Raw ????????????????????????????????????
     *
     * @param resId ?????? id
     * @param file  ??????????????????
     * @return {@link DevHelper}
     */
    public DevHelper saveRawFormFile(
            @RawRes final int resId,
            final File file
    ) {
        ResourceUtils.saveRawFormFile(resId, file);
        return this;
    }

    // ===============
    // = ScreenUtils =
    // ===============

    /**
     * ??????????????????
     *
     * @param activity {@link Activity}
     * @return {@link DevHelper}
     */
    public DevHelper setWindowSecure(final Activity activity) {
        ScreenUtils.setWindowSecure(activity);
        return this;
    }

    /**
     * ?????????????????????
     *
     * @param activity {@link Activity}
     * @return {@link DevHelper}
     */
    public DevHelper setFullScreen(final Activity activity) {
        ScreenUtils.setFullScreen(activity);
        return this;
    }

    /**
     * ??????????????????????????????
     *
     * @param activity {@link Activity}
     * @return {@link DevHelper}
     */
    public DevHelper setFullScreenNoTitle(final Activity activity) {
        ScreenUtils.setFullScreenNoTitle(activity);
        return this;
    }

    /**
     * ?????????????????????
     *
     * @param activity {@link Activity}
     * @return {@link DevHelper}
     */
    public DevHelper setLandscape(final Activity activity) {
        ScreenUtils.setLandscape(activity);
        return this;
    }

    /**
     * ?????????????????????
     *
     * @param activity {@link Activity}
     * @return {@link DevHelper}
     */
    public DevHelper setPortrait(final Activity activity) {
        ScreenUtils.setPortrait(activity);
        return this;
    }

    /**
     * ??????????????????
     *
     * @param activity {@link Activity}
     * @return {@link DevHelper}
     */
    public DevHelper toggleScreenOrientation(final Activity activity) {
        ScreenUtils.toggleScreenOrientation(activity);
        return this;
    }

    // =============
    // = SizeUtils =
    // =============

    /**
     * ??? onCreate ???????????????????????? ( ????????? onGetSizeListener ??????, ??? onGetSize ????????? View ?????? )
     *
     * @param view     {@link View}
     * @param listener {@link SizeUtils.OnGetSizeListener}
     * @return {@link DevHelper}
     */
    public DevHelper forceGetViewSize(
            final View view,
            final SizeUtils.OnGetSizeListener listener
    ) {
        SizeUtils.forceGetViewSize(view, listener);
        return this;
    }

    // ==================
    // = VibrationUtils =
    // ==================

    /**
     * ??????
     *
     * @param millis ???????????? ( ?????? )
     * @return {@link DevHelper}
     */
    @RequiresPermission(Manifest.permission.VIBRATE)
    public DevHelper vibrate(final long millis) {
        VibrationUtils.vibrate(millis);
        return this;
    }

    /**
     * pattern ????????????
     *
     * @param pattern new long[]{400, 800, 1200, 1600}, ??????????????? 400ms???800ms???1200ms???1600ms ???????????????????????????????????????????????????
     * @param repeat  ?????? pattern ???????????????, ?????? pattern ???????????? repeat ?????????????????????????????????,
     *                -1 ?????????????????????, ??? -1 ????????? pattern ????????????????????????????????????
     * @return {@link DevHelper}
     */
    @RequiresPermission(Manifest.permission.VIBRATE)
    public DevHelper vibrate(
            final long[] pattern,
            final int repeat
    ) {
        VibrationUtils.vibrate(pattern, repeat);
        return this;
    }

    /**
     * ????????????
     *
     * @return {@link DevHelper}
     */
    @RequiresPermission(Manifest.permission.VIBRATE)
    public DevHelper cancel() {
        VibrationUtils.cancel();
        return this;
    }

    // ==============
    // = CloseUtils =
    // ==============

    /**
     * ?????? IO
     *
     * @param closeables Closeable[]
     * @return {@link DevHelper}
     */
    public DevHelper closeIO(final Closeable... closeables) {
        CloseUtils.closeIO(closeables);
        return this;
    }

    /**
     * ???????????? IO
     *
     * @param closeables Closeable[]
     * @return {@link DevHelper}
     */
    public DevHelper closeIOQuietly(final Closeable... closeables) {
        CloseUtils.closeIOQuietly(closeables);
        return this;
    }

    /**
     * ????????????????????????
     *
     * @param flushables Flushable[]
     * @return {@link DevHelper}
     */
    public DevHelper flush(final Flushable... flushables) {
        CloseUtils.flush(flushables);
        return this;
    }

    /**
     * ??????????????????????????????
     *
     * @param flushables Flushable[]
     * @return {@link DevHelper}
     */
    public DevHelper flushQuietly(final Flushable... flushables) {
        CloseUtils.flushQuietly(flushables);
        return this;
    }

    /**
     * ????????????????????????????????????
     *
     * @param outputStream {@link OutputStream}
     * @return {@link DevHelper}
     */
    public DevHelper flushCloseIO(final OutputStream outputStream) {
        CloseUtils.flushCloseIO(outputStream);
        return this;
    }

    /**
     * ??????????????????????????????????????????
     *
     * @param outputStream {@link OutputStream}
     * @return {@link DevHelper}
     */
    public DevHelper flushCloseIOQuietly(final OutputStream outputStream) {
        CloseUtils.flushCloseIOQuietly(outputStream);
        return this;
    }

    /**
     * ????????????????????????????????????
     *
     * @param writer {@link Writer}
     * @return {@link DevHelper}
     */
    public DevHelper flushCloseIO(final Writer writer) {
        CloseUtils.flushCloseIO(writer);
        return this;
    }

    /**
     * ??????????????????????????????????????????
     *
     * @param writer {@link Writer}
     * @return {@link DevHelper}
     */
    public DevHelper flushCloseIOQuietly(final Writer writer) {
        CloseUtils.flushCloseIOQuietly(writer);
        return this;
    }

    // ==========================
    // = HttpURLConnectionUtils =
    // ==========================

    /**
     * ?????????????????? ( ???????????????????????? )
     *
     * @param callback ????????????????????????
     * @return {@link DevHelper}
     */
    public DevHelper getNetTime(final HttpURLConnectionUtils.TimeCallback callback) {
        HttpURLConnectionUtils.getNetTime(callback);
        return this;
    }

    /**
     * ??????????????????
     *
     * @param urlStr   ????????????
     * @param callback ????????????????????????
     * @return {@link DevHelper}
     */
    public DevHelper getNetTime(
            final String urlStr,
            final HttpURLConnectionUtils.TimeCallback callback
    ) {
        HttpURLConnectionUtils.getNetTime(urlStr, callback);
        return this;
    }

    // ==============
    // = TimeKeeper =
    // ==============

    /**
     * ???????????????????????????, ???????????? ( ?????? )
     *
     * @param keepTimeMillis ???????????? ( ?????? )
     * @param callback       ??????????????????
     * @return {@link DevHelper}
     */
    public DevHelper waitForEndAsync(
            final long keepTimeMillis,
            final TimeKeeper.OnEndCallback callback
    ) {
        mTimeKeeper.waitForEndAsync(keepTimeMillis, callback);
        return this;
    }

    /**
     * ???????????????????????????, ???????????? ( ?????? )
     *
     * @param keepTimeMillis ???????????? ( ?????? )
     * @param callback       ??????????????????
     * @return {@link DevHelper}
     */
    public DevHelper waitForEnd(
            final long keepTimeMillis,
            final TimeKeeper.OnEndCallback callback
    ) {
        mTimeKeeper.waitForEnd(keepTimeMillis, callback);
        return this;
    }

    // ==================
    // = AnimationUtils =
    // ==================

    /**
     * ??????????????????
     *
     * @param animation {@link Animation}
     * @param listener  {@link Animation.AnimationListener}
     * @return {@link DevHelper}
     */
    public DevHelper setAnimationListener(
            final Animation animation,
            final Animation.AnimationListener listener
    ) {
        AnimationUtils.setAnimationListener(animation, listener);
        return this;
    }
}