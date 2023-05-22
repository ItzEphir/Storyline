package com.ephirium.storyline.ui.recycler.callback;


import com.ephirium.storyline.model.Post;

import java.util.ArrayList;
import java.util.List;

import kotlin.Suppress;

public class PostCallback{
    private final List<OnClickCallback<Post>> onClickCallbacks = new ArrayList<>();
    private final List<OnMoveCallback> onMoveCallbacks = new ArrayList<>();
    private final List<OnSwipeCallback> onSwipeCallbacks = new ArrayList<>();

    public PostCallback(OnClickCallback<Post> callback){
        onClickCallbacks.add(callback);
    }

    @Suppress(names = "Unused")
    public PostCallback addOnClickCallback(OnClickCallback<Post> callback){
        onClickCallbacks.add(callback);
        return this;
    }

    @Suppress(names = "Unused")
    public PostCallback addOnMoveCallback(OnMoveCallback callback){
        onMoveCallbacks.add(callback);
        return this;
    }

    public PostCallback addOnSwipeCallback(OnSwipeCallback callback){
        onSwipeCallbacks.add(callback);
        return this;
    }

    public void onClick(Post post){
        onClickCallbacks.forEach(callback -> callback.onClick(post));
    }

    @Suppress(names = "Unused")
    public void onMove(int from, int to){
        onMoveCallbacks.forEach(callback -> callback.onMove(from, to));
    }

    @Suppress(names = "Unused")
    public void onSwipe(int direction, int position){
        onSwipeCallbacks.forEach(callback -> callback.onSwipe(direction, position));
    }
}
