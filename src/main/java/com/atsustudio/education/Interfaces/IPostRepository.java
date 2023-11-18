package com.atsustudio.education.Interfaces;

import com.atsustudio.education.Models.Post;
import org.springframework.data.repository.CrudRepository;

public interface IPostRepository extends CrudRepository<Post, Long> {
}
