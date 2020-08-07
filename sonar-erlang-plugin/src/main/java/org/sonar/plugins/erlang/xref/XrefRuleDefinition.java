/*
 * SonarQube Erlang Plugin
 * Copyright © 2012-2018 Tamas Kende <kende.tamas@gmail.com>
 * Copyright © 2018 Denes Hegedus (Cursor Insight Ltd.) <hegedenes@cursorinsight.com>
 * Copyright © 2020 Andris Raugulis <moo@arthepsy.eu>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sonar.plugins.erlang.xref;

import com.google.common.base.Charsets;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;
import org.sonar.plugins.erlang.core.Erlang;

public class XrefRuleDefinition implements RulesDefinition {

  public static final String REPOSITORY_NAME = "Xref";
  public static final String REPOSITORY_KEY = "xref";
  public static final String XREF_PATH = "/org/sonar/plugins/erlang/xref/rules.xml";

  private final RulesDefinitionXmlLoader xmlLoader;

  public XrefRuleDefinition(RulesDefinitionXmlLoader xmlLoader) {
    this.xmlLoader = xmlLoader;
  }

  @Override
  public void define(Context context) {
    NewRepository repository = context.createRepository(REPOSITORY_KEY, Erlang.KEY).setName(REPOSITORY_NAME);
    xmlLoader.load(repository, getClass().getResourceAsStream(XREF_PATH), Charsets.UTF_8.name());
    repository.done();
  }

}
