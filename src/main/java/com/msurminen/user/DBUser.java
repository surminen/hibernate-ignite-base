package com.msurminen.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

@SuppressWarnings("javadoc")
@Entity
@Table(name = "DBUSER")
public class DBUser implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

    @QuerySqlField
    private int userId;
    @QuerySqlField
    private String username;
    @QuerySqlField
    private String createdBy;
    @QuerySqlField
    private Date createdDate;

    public DBUser()
    {
    }

    public DBUser(int userId, String username, String createdBy, Date createdDate)
    {
        this.userId = userId;
        this.username = username;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    @Id
    @Column(name = "USER_ID", unique = true, nullable = false)
    public int getUserId()
    {
        return this.userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    @Column(name = "USERNAME", nullable = false, length = 20)
    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @Column(name = "CREATED_BY", nullable = false, length = 20)
    public String getCreatedBy()
    {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_DATE", nullable = false, length = 7)
    public Date getCreatedDate()
    {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
    }

}