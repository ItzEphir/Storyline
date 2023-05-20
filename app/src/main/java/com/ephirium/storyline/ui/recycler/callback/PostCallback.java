package com.ephirium.storyline.ui.recycler.callback;


import com.ephirium.storyline.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostCallback{
    private final List<PostOnClickCallback> onClickCallbacks = new ArrayList<>();
    private final List<PostOnMoveCallback> onMoveCallbacks = new ArrayList<>();
    private final List<PostOnSwipeCallback> onSwipeCallbacks = new ArrayList<>();

    public PostCallback(PostOnClickCallback callback){
        onClickCallbacks.add(callback);
    }

    public PostCallback addOnClickCallback(PostOnClickCallback callback){
        onClickCallbacks.add(callback);
        return this;
    }

    public PostCallback addOnMoveCallback(PostOnMoveCallback callback){
        onMoveCallbacks.add(callback);
        return this;
    }

    public PostCallback addOnSwipeCallback(PostOnSwipeCallback callback){
        onSwipeCallbacks.add(callback);
        return this;
    }

    public void onClick(Post post){
        onClickCallbacks.forEach(callback -> callback.onClick(post));
    }

    public void onMove(int from, int to){
        onMoveCallbacks.forEach(callback -> callback.onMove(from, to));
    }

    public void onSwipe(int direction, int position){
        onSwipeCallbacks.forEach(callback -> callback.onSwipe(direction, position));
    }
}
