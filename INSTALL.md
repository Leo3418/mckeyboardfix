# Installation Instructions

## Running Commands

This section is just a brief instruction on how to type and run commands on
various operating systems and in different types of shells. If you are familiar
with the terminal or command prompt on the operating system you will use to
build this mod, then you should skip this section.

If you are using the Command Prompt on Microsoft Windows, then when you are
asked to run a command that starts with `./`, you should omit the prefix and
type only what is after it. For example, if you are told to run
`./gradlew build`, then you should only type `gradlew build`.

In almost all other cases, you should run the command as-is. One of the cases
that is worth mentioning is Windows PowerShell.

## Dependencies of Building

Before building this mod, you need to install Java Development Kit 8 (JDK 8).
Please note that JDK 9 and any later JDK releases cause builds to fail.

As of this revision of this section is written, downloading Oracle JDK 8 from
Oracle's website requires signing in to an Oracle Account. If you do not want
to sign in, you can use OpenJDK 8 to build this mod. On a GNU/Linux
distribution, you should be able to install OpenJDK 8 using the package manager
on your system. Please consult the distribution's documentation or any other
helpful materials for details. On Windows, the binaries built by
[ojdkbuild](https://github.com/ojdkbuild/ojdkbuild) can provide OpenJDK 8.

## Setting up a Workspace

If this is the first time you compile a mod for the Minecraft Forge version
this mod is based on, then you need to set up a workspace for mod development
and compilation. You can get the Minecraft Forge version this mod depends on
from the `forge_version` property in the file gradle.properties.

You need to perform the set up only once for a single Minecraft Forge version.

To set up a workspace, open up a  terminal or command prompt window, and follow
these steps:
                                 
1. Make sure the working directory is the one containing this mod's sources;
use the `cd` command when necessary.
2. Run `./gradlew setupDecompWorkspace`. This step might take a few minutes,
depending on your computer's performance.

## Compiling This Mod

Once you have set up a workspace, open up a terminal or command prompt window,
and follow these steps:

1. Make sure the working directory is the one containing this mod's sources;
use the `cd` command when necessary.
2. Run `./gradlew build`.
3. Under the directory `build/libs`, the JAR file whose name does not contain
"sources" is the binary of this mod.

## Installing This Mod

The following instruction assumes you are installing this mod on GNU/Linux
since the mod is designated to be installed on that platform.

If you have not installed Minecraft Forge for the Minecraft version you will
play on, then please download the "Installer" for that version from their
[website](https://files.minecraftforge.net/). To run the downloaded installer
FILE, use the shell command `java -jar FILE`.

Before installing this mod, you need to confirm the game directory of the
Minecraft Forge version that you want to use. In Minecraft Launcher, edit the
launcher profile for that version, and you can see the game directory for the
profile. The default game directory is `~/.minecraft/`, and any relative path
entered for the game directory option is relative to the default one. 

You may install this mod to one of the following paths under the game
directory:
- `mods`. Once you install this mod to this path, it will be loaded for every
launcher profile that uses the same game directory, so it is recommended for
users who install multiple Minecraft versions concurrently. However, you cannot
specify the same game directory for a Minecraft Forge version that this mod is
incompatible with.
- `mods/1.x.y` where `1.x.y` is the Minecraft version you will play on. For
example, if you will use Minecraft 1.12.2, then the path is `mods/1.12.2`. Omit
the `.y` part if necessary, like for Minecraft 1.9, the path is `mods/1.9`.

To install this mod, you just need to copy its binary to the installation path
you have chosen.
