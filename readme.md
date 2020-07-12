# XJam

XJam is an esoteric programming language built on [CJam](https://sourceforge.net/p/cjam/wiki/Home/). It is being adapted for two main purposes:

1. Be the first (?) language to conform to XKCD's [X](https://xkcd.com/2309/) in some way. Although X seems to be talking about actual fonts, XJam uses font-like variations of the letter X that are in Unicode, mostly from the Mathematical Alphanumeric Symbols block.
2. Be slightly more useful for [code golfing](https://en.wikipedia.org/wiki/Code_golf). XJam will introduce a little more functionality, such as constants (all capital letters in CJam except X cannot be modified and are effectively constants), ✕ (equivalent to q" "/~ in CJam), ✗ (equivalent to ea0= in CJam), and 𝔁 (a new variable, initialized equivalent to e).

codepoints.txt contains a list of the current characters (128 of them) in order of what their codepoint mappings should be.
variables.csv documents the differences between variables in CJam and XJam.

In the future, more joke functionality is also planned, such as a function that is supposed to be passed a password and [crashes if it is a resolvable URL](https://xkcd.com/1700/). Based on the alt-text, maybe the password will be otherwise salted with emoji!
