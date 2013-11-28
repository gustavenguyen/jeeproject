package com.jeeproject.Models;

import java.util.List;

import com.jeeproject.Entities.Comment;


public interface CommentDAO {


public List <Comment> getCommentsByAlbum(int album_id);
public void addComment(Comment newComment);


}
