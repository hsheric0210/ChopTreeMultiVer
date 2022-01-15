package net.gkid117;

public class ChopTreePlayer
{
	private static ChopTree plugin;
	private boolean active;
	private String playerName;

	public ChopTreePlayer(ChopTree instance, String playerName)
	{
		plugin = instance;
		this.playerName = playerName;
		this.active = getSetting("active");
		if (plugin.configuration.get(playerName + ".active") == null)
		{
			addPlayer();
			this.active = plugin.defaultActive;
		}
	}

	public boolean isActive()
	{
		return this.active;
	}

	public void setActive(boolean setting)
	{
		this.active = setting;
		plugin.configuration.set(this.playerName + ".active", Boolean.valueOf(setting));
		plugin.savePlayers();
	}

	public void toggleActive()
	{
		if (this.active)
		{
			this.active = false;
		}
		else
		{
			this.active = true;
		}
		plugin.configuration.set(this.playerName + ".active", Boolean.valueOf(this.active));
		plugin.savePlayers();
	}

	private boolean getSetting(String setting)
	{
		boolean value = false;
		if (setting.equalsIgnoreCase("active"))
		{
			value = plugin.configuration.getBoolean(this.playerName + "." + setting, plugin.defaultActive);
		}
		return value;
	}

	private void addPlayer()
	{
		plugin.configuration.set(this.playerName + ".active", Boolean.valueOf(plugin.defaultActive));
		plugin.savePlayers();
	}
}
