/**************************************************************************
 OmegaT - Computer Assisted Translation (CAT) tool
          with fuzzy matching, translation memory, keyword search,
          glossaries, and translation leveraging into updated projects.

 Copyright (C) 2000-2006 Keith Godfrey and Maxym Mykhalchuk
               Home page: https://www.omegat.org/
               Support center: https://omegat.org/support

 This file is part of OmegaT.

 OmegaT is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 OmegaT is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <https://www.gnu.org/licenses/>.
 **************************************************************************/

package org.omegat.filters3.xml;

import org.omegat.filters3.Text;
import org.omegat.util.StringUtil;

/**
 * Piece of text in XML.
 *
 * @author Maxym Mykhalchuk
 */
public class XMLText extends Text {
    private boolean inCDATA;

    /** Whether this text is inside XDATA section. */
    public boolean isInCDATA() {
        return inCDATA;
    }

    /** Creates a piece of XML text. */
    public XMLText(String text, boolean inCDATA) {
        super(text);
        this.inCDATA = inCDATA;
    }

    /**
     * Returns the text in its original form as it was in original document.
     * E.g. for <code>Rock&Roll</code> should return <code>Rock&amp;Roll</code>.
     */
    public String toOriginal() {
        if (inCDATA) {
            StringBuilder res = new StringBuilder();
            res.append("<![CDATA[");
            res.append(getText());
            res.append("]]>");
            return res.toString();
        } else {
            return StringUtil.makeValidXML(getText());
        }
    }

    /**
     * Creates a new instance of XMLText class.
     */
    public Text createInstance(String text) {
        return new XMLText(text, inCDATA);
    }
}
