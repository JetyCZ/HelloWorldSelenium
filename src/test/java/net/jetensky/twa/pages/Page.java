package net.jetensky.twa.pages;

import net.jetensky.twa.ObjectWithWebDriverParent;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class Page extends ObjectWithWebDriverParent{

  public String getTitle() {
    return driver().getTitle();
  }

}
