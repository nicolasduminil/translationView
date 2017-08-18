package com.coface.corp.translationView.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.coface.corp.translationView.model.Message;

@XmlRootElement
public class MessageList
{
  private List<Message> messages;

  public MessageList()
  {
  }

  public MessageList(List<Message> messages)
  {
    super();
    this.messages = messages;
  }

  public List<Message> getMessages()
  {
    return messages;
  }

  public void setMessages(List<Message> messages)
  {
    this.messages = messages;
  }
}
