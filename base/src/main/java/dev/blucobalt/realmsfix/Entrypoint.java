/**
 * This file is part of realmsfix.
 * <p>
 * realmsfix is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 * <p>
 * realmsfix is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License
 * along with realmsfix.  If not, see <http://www.gnu.org/licenses/>.
 */
package dev.blucobalt.realmsfix;


import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixins;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;


public class Entrypoint
    implements PreLaunchEntrypoint
{
    private static final Map<String, String> VERSION_MAP = new LinkedHashMap<>();
    private static final Logger              LOGGER      = LogManager.getLogger("realmsfix");

    static {
        VERSION_MAP.put("1.21.4", "1.21.2");
        VERSION_MAP.put("1.21.3", "1.21.2");
        VERSION_MAP.put("1.21.2", "1.21.2");
        VERSION_MAP.put("1.21", "1.21");
        VERSION_MAP.put("1.20", "1.20.4");
        VERSION_MAP.put("1.19", "1.19.4");
        VERSION_MAP.put("1.18", "1.18.2");
        VERSION_MAP.put("1.17", "1.17.1");
        VERSION_MAP.put("1.16", "1.16.5");
        VERSION_MAP.put("1.15", "1.15.2");
        VERSION_MAP.put("1.14", "1.14.4");
        VERSION_MAP.put("1.13", "1.13.2");
        VERSION_MAP.put("1.12", "1.12.2");
        VERSION_MAP.put("1.11", "1.11.2");
        VERSION_MAP.put("1.10", "1.10.2");
        VERSION_MAP.put("1.9", "1.9.4");
        VERSION_MAP.put("1.8", "1.8.9");
        VERSION_MAP.put("1.7", "1.7.10");
    }

    private static String computedVersion;

    @Override
    public void onPreLaunch()
    {
        @SuppressWarnings("OptionalGetWithoutIsPresent") // minecraft is always going to be present
        final String version = FabricLoader.getInstance().getModContainer("minecraft").get().getMetadata().getVersion().getFriendlyString();

        for (Entry<String, String> entry : VERSION_MAP.entrySet()) {
            if (version.startsWith(entry.getKey())) {
                computedVersion = entry.getValue();
                break;
            }
        }

        LOGGER.info("resolved config: " + "realmsfix-" + computedVersion + ".mixins.json");
        Mixins.addConfiguration("realmsfix-" + computedVersion + ".mixins.json");
    }
}
