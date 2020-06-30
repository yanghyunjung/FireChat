package net.skhu.firechat2.FirebaseDBService.Storage;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import net.skhu.firechat2.FirebaseDBService.FireBaseReference;
import net.skhu.firechat2.ListenerInterface.RoomChatListener.OnFileDownloadCompleteListener;
import net.skhu.firechat2.ListenerInterface.RoomChatListener.OnFileUploadCompleteListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FirebaseStorageService {
    public static void videoDownload(Context context, File path, String downloadFileName, OnFileDownloadCompleteListener OnFileDownloadCompleted) {
        //downloadFileName = itemList.get(selectIndex).getVideoFileName();

        com.google.firebase.storage.FirebaseStorage storage = com.google.firebase.storage.FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl(FireBaseReference.FIREBASE_STORAGE_REF).child("videos/" + downloadFileName);

        try {

            //로컬에 저장할 폴더의 위치
            //path = getFilesDir();

            //저장하는 파일의 이름
            final File file = new File(path, downloadFileName);
            try {
                if (!path.exists()) {
                    //저장할 폴더가 없으면 생성
                    path.mkdirs();
                }

                if (!file.exists()) {
                    final ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setTitle("다운로드중...");
                    progressDialog.show();

                    file.createNewFile();
                    //파일을 다운로드하는 Task 생성, 비동기식으로 진행
                    final FileDownloadTask fileDownloadTask = storageRef.getFile(file);
                    fileDownloadTask.addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        OnFileDownloadCompleteListener onFileDownloadCompleteListener = OnFileDownloadCompleted;

                        //int VideoIndex = index;
                        //String videoFileName = item.getVideoFileName();
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss(); //업로드 진행 Dialog 상자 닫기
                            //다운로드 성공 후 할 일
                            onFileDownloadCompleteListener.onFileDownloadCompleteListener();

                            Log.v("pjw", file.getPath() + "다운로드 성공");
                            //roomChatRecyclerViewAdapter.notifyItemChanged(VideoIndex);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //다운로드 실패 후 할 일
                            Log.v("pjw", file.getPath() + "다운로드 실패");
                        }
                    }).addOnProgressListener(new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        //진행상태 표시
                        public void onProgress(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            @SuppressWarnings("VisibleForTests") //이걸 넣어 줘야 아랫줄에 에러가 사라진다. 넌 누구냐?
                                    double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            //dialog에 진행률을 퍼센트로 출력해 준다
                            progressDialog.setMessage("download " + ((int) progress) + "% ...");
                        }
                    });
                } else {

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void audioDownload(Context context, File path, String downloadFileName, OnFileDownloadCompleteListener OnFileDownloadCompleted) {
        //downloadFileName = itemList.get(selectIndex).getVideoFileName();

        com.google.firebase.storage.FirebaseStorage storage = com.google.firebase.storage.FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl(FireBaseReference.FIREBASE_STORAGE_REF).child("audio/" + downloadFileName);

        try {

            //로컬에 저장할 폴더의 위치
            //path = getFilesDir();

            //저장하는 파일의 이름
            final File file = new File(path, downloadFileName);
            try {
                if (!path.exists()) {
                    //저장할 폴더가 없으면 생성
                    path.mkdirs();
                }

                if (!file.exists()) {
                    final ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setTitle("다운로드중...");
                    progressDialog.show();

                    file.createNewFile();
                    //파일을 다운로드하는 Task 생성, 비동기식으로 진행
                    final FileDownloadTask fileDownloadTask = storageRef.getFile(file);
                    fileDownloadTask.addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        OnFileDownloadCompleteListener onFileDownloadCompleteListener = OnFileDownloadCompleted;

                        //int VideoIndex = index;
                        //String videoFileName = item.getVideoFileName();
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss(); //업로드 진행 Dialog 상자 닫기
                            //다운로드 성공 후 할 일
                            onFileDownloadCompleteListener.onFileDownloadCompleteListener();

                            Log.v("pjw", file.getPath() + "다운로드 성공");
                            //roomChatRecyclerViewAdapter.notifyItemChanged(VideoIndex);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //다운로드 실패 후 할 일
                            Log.v("pjw", file.getPath() + "다운로드 실패");
                        }
                    }).addOnProgressListener(new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        //진행상태 표시
                        public void onProgress(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            @SuppressWarnings("VisibleForTests") //이걸 넣어 줘야 아랫줄에 에러가 사라진다. 넌 누구냐?
                                    double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            //dialog에 진행률을 퍼센트로 출력해 준다
                            progressDialog.setMessage("download " + ((int) progress) + "% ...");
                        }
                    });
                } else {

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void imagesDownload(Context context, File path, String downloadFileName, OnFileDownloadCompleteListener OnFileDownloadCompleted) {
        //downloadFileName = itemList.get(selectIndex).getVideoFileName();

        com.google.firebase.storage.FirebaseStorage storage = com.google.firebase.storage.FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl(FireBaseReference.FIREBASE_STORAGE_REF).child("images/" + downloadFileName);

        try {

            //로컬에 저장할 폴더의 위치
            //path = getFilesDir();

            //저장하는 파일의 이름
            final File file = new File(path, downloadFileName);
            try {
                if (!path.exists()) {
                    //저장할 폴더가 없으면 생성
                    path.mkdirs();
                }

                if (!file.exists()) {
                    // final ProgressDialog progressDialog = new ProgressDialog(context);
                    //progressDialog.setTitle("다운로드중...");
                    // progressDialog.show();

                    file.createNewFile();
                    //파일을 다운로드하는 Task 생성, 비동기식으로 진행
                    final FileDownloadTask fileDownloadTask = storageRef.getFile(file);
                    fileDownloadTask.addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        OnFileDownloadCompleteListener onFileDownloadCompleteListener = OnFileDownloadCompleted;

                        //int VideoIndex = index;
                        //String videoFileName = item.getVideoFileName();
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            //progressDialog.dismiss(); //업로드 진행 Dialog 상자 닫기
                            //다운로드 성공 후 할 일
                            onFileDownloadCompleteListener.onFileDownloadCompleteListener();

                            Log.v("pjw", file.getPath() + "다운로드 성공");
                            //roomChatRecyclerViewAdapter.notifyItemChanged(VideoIndex);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //다운로드 실패 후 할 일
                            Log.v("pjw", file.getPath() + "다운로드 실패");
                        }
                    }).addOnProgressListener(new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        //진행상태 표시
                        public void onProgress(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            @SuppressWarnings("VisibleForTests") //이걸 넣어 줘야 아랫줄에 에러가 사라진다. 넌 누구냐?
                                    double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            //dialog에 진행률을 퍼센트로 출력해 준다
                            //progressDialog.setMessage("download " + ((int) progress) + "% ...");
                        }
                    });
                } else {

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void imagesDownload(Context context, File path, String downloadFileName) {
        //downloadFileName = itemList.get(selectIndex).getVideoFileName();

        com.google.firebase.storage.FirebaseStorage storage = com.google.firebase.storage.FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl(FireBaseReference.FIREBASE_STORAGE_REF).child("images/" + downloadFileName);

        try {

            //로컬에 저장할 폴더의 위치
            //path = getFilesDir();

            //저장하는 파일의 이름
            final File file = new File(path, downloadFileName);
            try {
                if (!path.exists()) {
                    //저장할 폴더가 없으면 생성
                    path.mkdirs();
                }

                if (!file.exists()) {
                    // final ProgressDialog progressDialog = new ProgressDialog(context);
                    //progressDialog.setTitle("다운로드중...");
                    // progressDialog.show();

                    file.createNewFile();
                    //파일을 다운로드하는 Task 생성, 비동기식으로 진행
                    final FileDownloadTask fileDownloadTask = storageRef.getFile(file);
                    fileDownloadTask.addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {

                        //int VideoIndex = index;
                        //String videoFileName = item.getVideoFileName();
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            //progressDialog.dismiss(); //업로드 진행 Dialog 상자 닫기

                            Log.v("pjw", file.getPath() + "다운로드 성공");
                            //roomChatRecyclerViewAdapter.notifyItemChanged(VideoIndex);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //다운로드 실패 후 할 일
                            Log.v("pjw", file.getPath() + "다운로드 실패");
                        }
                    }).addOnProgressListener(new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        //진행상태 표시
                        public void onProgress(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            @SuppressWarnings("VisibleForTests") //이걸 넣어 줘야 아랫줄에 에러가 사라진다. 넌 누구냐?
                                    double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            //dialog에 진행률을 퍼센트로 출력해 준다
                            //progressDialog.setMessage("download " + ((int) progress) + "% ...");
                        }
                    });
                } else {

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void videoUpload(Context context, Uri filePath, String mediaType, OnFileUploadCompleteListener OnFileUploadCompleted) {

        String fileName;
        //업로드 진행 Dialog 보이기
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("업로드중...");
        progressDialog.show();

        //storage
        FirebaseStorage storage = FirebaseStorage.getInstance();

        //Unique한 파일명을 만들자.
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMHH_mmss.SSS");
        Date now = new Date();
        fileName = formatter.format(now) + mediaType;
        //storage 주소와 폴더 파일명을 지정해 준다.
        StorageReference storageRef = storage.getReferenceFromUrl(FireBaseReference.FIREBASE_STORAGE_REF).child("videos/" + fileName);

        //올라가거라...
        storageRef.putFile(filePath)
                //성공시
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    OnFileUploadCompleteListener onFileUploadCompleteListener = OnFileUploadCompleted;
                    String uploadFilename = fileName;

                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss(); //업로드 진행 Dialog 상자 닫기
                        //Toast.makeText(getApplicationContext(), "업로드 완료!", Toast.LENGTH_SHORT).show();
                        Log.v("pjw", "업로드 완료!");
                            /*
                           // Uri externalUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                            String[] projection = new String[]{
                                    MediaStore.Video.Media._ID,
                                    MediaStore.Video.Media.DISPLAY_NAME,
                                    MediaStore.Video.Media.MIME_TYPE
                            };

                            Cursor cursor = getContentResolver().query(filePath, projection, null, null, null);

                            if (cursor == null || !cursor.moveToFirst()) {
                                Log.e("pjw", "cursor null or cursor is empty");
                            }
                            else {
                                do {
                                    String contentUrl = filePath.toString() + "/" + cursor.getString(0);
                                    Log.v("pjw", "contentUrl: " + contentUrl);

                                    path = getFilesDir();

                                    //저장하는 파일의 이름
                                    final File file = new File(path, filename);

                                    try {
                                        file.createNewFile();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    try {

                                        FileInputStream inputStream = new FileInputStream(contentUrl);

                                        FileOutputStream outputStream = new FileOutputStream(file);

                                        int bytesRead = 0;

                                        byte[] buffer = new byte[1024];

                                        while ((bytesRead = inputStream.read(buffer, 0, 1024)) != -1) {
                                            outputStream.write(buffer, 0, bytesRead);
                                        }

                                        outputStream.close();

                                        inputStream.close();

                                    } catch (FileNotFoundException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }

                                    Log.v("pjw", "\nfile Path " + file.toString());

                                } while (cursor.moveToNext());
                            }*/


                        onFileUploadCompleteListener.onFileUploadCompleteListener(uploadFilename);
//                        Intent intent = new Intent();
//                        intent.putExtra("downloadVideoFileName", filename);
//                        setResult(Activity.RESULT_OK, intent);
//
//                        finish();
                    }
                })
                //실패시
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        //Toast.makeText(getApplicationContext(), "업로드 실패!", Toast.LENGTH_SHORT).show();
                        Log.v("pjw", "업로드 실패!");
                    }
                })
                //진행중
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        @SuppressWarnings("VisibleForTests") //이걸 넣어 줘야 아랫줄에 에러가 사라진다. 넌 누구냐?
                                double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        //dialog에 진행률을 퍼센트로 출력해 준다
                        progressDialog.setMessage("Uploaded " + ((int) progress) + "% ...");
                    }
                });
    }

    public static void audioUpload(Context context, Uri filePath, String mediaType, OnFileUploadCompleteListener OnFileUploadCompleted) {

        String fileName;
        //업로드 진행 Dialog 보이기
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("업로드중...");
        progressDialog.show();

        //storage
        FirebaseStorage storage = FirebaseStorage.getInstance();

        //Unique한 파일명을 만들자.
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMHH_mmss.SSS");
        Date now = new Date();
        fileName = formatter.format(now) + mediaType;
        //storage 주소와 폴더 파일명을 지정해 준다.
        StorageReference storageRef = storage.getReferenceFromUrl(FireBaseReference.FIREBASE_STORAGE_REF).child("audio/" + fileName);

        //올라가거라...
        storageRef.putFile(filePath)
                //성공시
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    String uploadFileName = fileName;
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss(); //업로드 진행 Dialog 상자 닫기
                        //Toast.makeText(getApplicationContext(), "업로드 완료!", Toast.LENGTH_SHORT).show();
                        Log.v("pjw", "업로드 완료!");


                            /*try {
                                path = getFilesDir();

                                //저장하는 파일의 이름
                                final File file = new File(path, filename);
                                if (!file.exists()) {

                                    file.createNewFile();

                                    Cursor cursor2 = getContentResolver().query(filePath, null, null, null);
                                    cursor2.moveToNext();
                                    String pathStr = cursor2.getString(cursor2.getColumnIndex("_data"));
                                    cursor2.close();

                                    final File originalFile = new File(pathStr);

                                    String downloadVideoName = filename;

                                    Log.v("pjw", "\noriginalFile Path " + originalFile.toString());
                                    Log.v("pjw", "\nfile Path " + file.toString());

                                    try {

                                        FileInputStream inputStream = new FileInputStream(originalFile);

                                        FileOutputStream outputStream = new FileOutputStream(file);

                                        int bytesRead = 0;

                                        byte[] buffer = new byte[1024];

                                        while ((bytesRead = inputStream.read(buffer, 0, 1024)) != -1) {
                                            outputStream.write(buffer, 0, bytesRead);
                                        }

                                        outputStream.close();

                                        inputStream.close();

                                    } catch (FileNotFoundException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                }
                            } catch (FileNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }*/

                        OnFileUploadCompleted.onFileUploadCompleteListener(uploadFileName);
//                        Intent intent = new Intent();
//                        intent.putExtra("downloadMusicFileName", filename);
//                        setResult(Activity.RESULT_OK, intent);
//
//                        finish();
                    }
                })
                //실패시
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        //Toast.makeText(getApplicationContext(), "업로드 실패!", Toast.LENGTH_SHORT).show();
                        Log.v("pjw", "업로드 실패!");
                    }
                })
                //진행중
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        @SuppressWarnings("VisibleForTests") //이걸 넣어 줘야 아랫줄에 에러가 사라진다. 넌 누구냐?
                                double progress = (100 * taskSnapshot.getBytesTransferred()) /  taskSnapshot.getTotalByteCount();
                        //dialog에 진행률을 퍼센트로 출력해 준다
                        progressDialog.setMessage("Uploaded " + ((int) progress) + "% ...");
                    }
                });
    }

    public static void imageUpload(Context context, Uri filePath, OnFileUploadCompleteListener OnFileUploadCompleted) {

        String fileName;
        //업로드 진행 Dialog 보이기
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("업로드중...");
        progressDialog.show();

        //storage
        FirebaseStorage storage = FirebaseStorage.getInstance();

        //Unique한 파일명을 만들자.
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMHH_mmss.SSS");
        Date now = new Date();
        fileName = formatter.format(now) + ".png";
        //storage 주소와 폴더 파일명을 지정해 준다.
        StorageReference storageRef = storage.getReferenceFromUrl(FireBaseReference.FIREBASE_STORAGE_REF).child("images/" + fileName);

        //올라가거라...
        storageRef.putFile(filePath)
                //성공시
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    String uploadFileName = fileName;
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss(); //업로드 진행 Dialog 상자 닫기
                        //Toast.makeText(getApplicationContext(), "업로드 완료!", Toast.LENGTH_SHORT).show();
                        Log.v("pjw", "업로드 완료!");

                        OnFileUploadCompleted.onFileUploadCompleteListener(uploadFileName);

//                        //Uri externalUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//                        String[] projection = new String[]{
//                                MediaStore.Images.Media._ID,
//                                MediaStore.Images.Media.DISPLAY_NAME,
//                                MediaStore.Images.Media.MIME_TYPE
//                        };
//
//                        Cursor cursor = getContentResolver().query(filePath, projection, null, null, null);
//
//                        if (cursor == null || !cursor.moveToFirst()) {
//                            Log.e("pjw", "cursor null or cursor is empty");
//                        }
//                        else {
//                            do {
//                                String contentUrl = filePath.toString() + "/" + cursor.getString(0);
//
//                                Log.v("pjw", "contentUrl" + contentUrl);
//
//                                path = getFilesDir();
//
//                                //저장하는 파일의 이름
//                                final File file = new File(path, filename);
//
//                                try {
//                                    file.createNewFile();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//
//                                /*Cursor cursor = getContentResolver().query(filePath, null, null, null);
//                                cursor.moveToNext();
//                                String pathStr = cursor.getString(cursor.getColumnIndex("_data"));
//                                cursor.close();
//
//                                final File originalFile = new File(pathStr);
//
//                                String downloadVideoName = filename;
//
//                                Log.v("pjw", "\noriginalFile Path " + originalFile.toString());
//                                Log.v("pjw", "\nfile Path " + file.toString());*/
//
//                                try {
//
//                                    FileInputStream inputStream = new FileInputStream(contentUrl);
//
//                                    FileOutputStream outputStream = new FileOutputStream(file);
//
//                                    int bytesRead = 0;
//
//                                    byte[] buffer = new byte[1024];
//
//                                    while ((bytesRead = inputStream.read(buffer, 0, 1024)) != -1) {
//                                        outputStream.write(buffer, 0, bytesRead);
//                                    }
//
//                                    outputStream.close();
//
//                                    inputStream.close();
//
//                                } catch (FileNotFoundException e) {
//                                    // TODO Auto-generated catch block
//                                    e.printStackTrace();
//                                } catch (IOException e) {
//                                    // TODO Auto-generated catch block
//                                    e.printStackTrace();
//                                }
//
//                                Log.v("pjw", "\nfile Path " + file.toString());
//
//                                /*try {
//                                    InputStream is = getContentResolver().openInputStream(Uri.parse(contentUrl));
//                                    OutputStream os;
//
//
//                                    int data = 0;
//                                    StringBuilder sb = new StringBuilder();
//
//                                    while ((data = is.read()) != -1) {
//                                        sb.append((char) data);
//                                    }
//
//                                    is.close();
//
//                                } catch (FileNotFoundException e) {
//                                    e.printStackTrace();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }*/
//
//                            } while (cursor.moveToNext());
//                        }
//
//                        Intent intent = new Intent();
//                        intent.putExtra("downloadFileName", filename);
//                        //intent.putExtra("originalFilePath", pathStr);
//                        setResult(Activity.RESULT_OK, intent);
//
//                        finish();
                    }
                })
                //실패시
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        //Toast.makeText(getApplicationContext(), "업로드 실패!", Toast.LENGTH_SHORT).show();
                        Log.v("pjw", "업로드 실패!");
                    }
                })
                //진행중
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        @SuppressWarnings("VisibleForTests") //이걸 넣어 줘야 아랫줄에 에러가 사라진다. 넌 누구냐?
                                double progress = (100 * taskSnapshot.getBytesTransferred()) /  taskSnapshot.getTotalByteCount();
                        //dialog에 진행률을 퍼센트로 출력해 준다
                        progressDialog.setMessage("Uploaded " + ((int) progress) + "% ...");
                    }
                });
    }
}
