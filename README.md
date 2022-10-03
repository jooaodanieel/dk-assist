![DKAssist logo](DKAssistLogo.png)

![GitHub Workflow Status](https://img.shields.io/github/workflow/status/jooaodanieel/dk-assist/Create%20a%20new%20GitHub%20Release?style=for-the-badge) ![GitHub release (latest by date)](https://img.shields.io/github/v/release/jooaodanieel/dk-assist?style=for-the-badge)  ![GitHub all releases](https://img.shields.io/github/downloads/jooaodanieel/dk-assist/total?color=orange&style=for-the-badge)

DKAssist is a tool that helps devs to set up a repository with its configs, such as `.env` files.

With a simple `Assistfile.json`, you can document the env-vars with name, a brief description, and default values,
as well as provide a check-list of other files needed to build the application.  

## Installation

There are three methods for installing dk-assist

> Windows users, help us! We couldn't set the releases for Windows :(

### 1) AUR

dk-assist is available at the [Arch User Repository][aur], so you can easily install via `yay`

```bash
yay -S dk-assist-git
```

### 2) GitHub Releases

First, ensure you have `libcrypt.so.1` available in your system.

Then head to [releases][releases] page, download the latest `dk-assist-<OS>-latest.kexe` and store into within your `$PATH`.
We suggest you call it `dk-assist`, and make sure it is executable :) You can also store it in a project's roots directory,
but then you'll have to call it `./dk-assist` instead of calling it directly.


### 3) Manual build

If you're interested in building it manually,

```bash
# build the DKAssist executable
./gradlew build

# copy to your desired directory
cp build/bin/native/releaseExecutable/dk-assist.kexe /path/to/dk-assist
```

## How to use

DKAssist relies on `Assistfile.json`, in which you define `envSample` and `requiredFiles`. 

### Commands

With the `Assistfile.json` set, we can use `dk-assist` to our help:

- `dk-assist init` will generate a ready-to-use `Assistfile.json` with empty `requiredFiles` and `envSample` 
- `dk-assist scaffold` will generate all the `requiredFiles` listed, so you can easily fill them
- `dk-assist report` will display the status of your repository regarding the required files, informing which of the files need to be filled yet (and which have already been filled)


### Configuration

Below we present an example of an `Assistfile.json`

```json
{
  "requiredFiles": [
    {
      "path": "./config/master.key",
      "description": "Encryption key for Rails credentials"
    },
    {
      "path": "./.env"
    }
  ],
  "envSample": [
    {
      "name": "DATABASE_CONNECTION_URL"
    },
    {
      "name": "CORS_ENABLED",
      "description": "The address(es) enabled by CORS; in case of multiple, separate with ';' (semi-colon) and no spaces, e.g., 'http://something.com;http://other.com'; in case of enabling all, use '*'",
      "default": "*"
    }
  ]
}
```

In this example, the `requiredFiles` are a

- `./config/master.key`: a file Rails projects use for encrypting credentials, and that **should not** be tracked by VCS
- `./.env`: the definition of the env-vars

In more details, the example `Assistfile.json` informs a sample of the `.env` file

- `DATABASE_CONNECTION_URL` which has no description since its very readable and clear
- `CORS_ENABLED` to set the list of hosts that are allowed to request; its default is `"*"`

As we can see, both `requiredFiles` and `envSample` elements require a `name`, and accept a `description` as documentation.
Also, `envSample` elements accept a `default` value.



## Contributing

Please refer to [CONTRIBUTING.md][1]


## License

This project is licensed under the [MIT License][2]




[1]: https://github.com/jooaodanieel/dk-assist/blob/master/CONTRIBUTING.md
[2]: https://opensource.org/licenses/MIT


[aur]: https://aur.archlinux.org/packages/dk-assist-git
[releases]: https://github.com/jooaodanieel/dk-assist/releases
