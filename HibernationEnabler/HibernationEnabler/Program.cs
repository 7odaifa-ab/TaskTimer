Console.WriteLine("");
Console.WriteLine(" a88888b .d88888P                dP          oo .8888b                                dP       \r");
Console.WriteLine("d8'   `88     d8'                88             88   \"                                88       \r");
Console.WriteLine("88 d8P 88    d8'  .d8888b. .d888b88 .d8888b. dP 88aaa  .d8888b.              .d8888b. 88d888b. \r");
Console.WriteLine("88 Yo8b88   d8'   88'  `88 88'  `88 88'  `88 88 88     88'  `88              88'  `88 88'  `88 \r");
Console.WriteLine("Y8.        d8'    88.  .88 88.  .88 88.  .88 88 88     88.  .88              88.  .88 88.  .88 \r");
Console.WriteLine(" Y88888P' d8'     `88888P' `88888P8 `88888P8 dP dP     `88888P8              `88888P8 88Y8888' \r");
Console.WriteLine("                                                                oooooooooooo                   \r");
Console.WriteLine("");

if (args.Length > 0)
{
    foreach (var arg in args)
    {
        System.Diagnostics.Process process = new System.Diagnostics.Process();
        System.Diagnostics.ProcessStartInfo startInfo = new System.Diagnostics.ProcessStartInfo
        {
            WindowStyle = System.Diagnostics.ProcessWindowStyle.Hidden,
            FileName = "cmd.exe"
        };

        if (arg == "enable")
            startInfo.Arguments = "/C powercfg -H on";
        else if (arg == "disable")
            startInfo.Arguments = "/C powercfg -H off";

        process.StartInfo = startInfo;
        process.Start();
        Console.WriteLine($"Argument = {arg}");
    }
}
else
{
    Console.WriteLine("No arguments");
}

Console.ReadLine();